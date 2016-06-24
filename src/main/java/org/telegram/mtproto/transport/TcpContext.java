package org.telegram.mtproto.transport;

import org.telegram.mtproto.MTProto;
import org.telegram.mtproto.log.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import jawnae.pyronet.PyroClient;
import jawnae.pyronet.PyroClientListener;
import jawnae.pyronet.PyroSelector;

public class TcpContext implements PyroClientListener {
    private static volatile Integer nextChannelToken = 1;
    private static final int MAX_PACKED_SIZE = 1024 * 1024 * 1024;//1 MB
    private static final AtomicInteger contextLastId = new AtomicInteger(1);
    private static final int CONNECTION_TIMEOUT = 30000;

    private static int generateChannelToken() {
        return nextChannelToken++;
    }

    private ConnectionState connectionState;
    private int failedConnectionCount;
    private int willRetryConnectCount = 5;
    private boolean hasSomeDataSinceLastConnect = false;
    private int channelToken = 0;
    private final Object timerSync = new Object();
    private Timer reconnectTimer;
    private boolean isFirstPackage = true;

    private final String TAG;
    private final String ip;
    private final int port;
    private final int contextId;
    private int sentPackets;

    private PyroSelector selector;
    private PyroClient client;
    private ByteBufferDesc restOfTheData;
    private int lastPacketLength;

    private TcpContextCallback callback;

    public TcpContext(MTProto proto, String ip, int port, TcpContextCallback callback) {
        this.contextId = contextLastId.incrementAndGet();
        this.connectionState = ConnectionState.TcpConnectionStageIdle;
        this.TAG = "MTProto#" + proto.getInstanceIndex() + "#Transport" + this.contextId;
        this.ip = ip;
        this.port = port;
        this.callback = callback;
        this.selector = new PyroSelector();
        this.selector.spawnNetworkThread("Selector Thread");
        BuffersStorage.getInstance();
    }

    private void readData(ByteBuffer buffer) throws Exception {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.rewind();

        ByteBuffer parseLaterBuffer = null;
        if (restOfTheData != null) {
            if (lastPacketLength == 0) {
                if ((restOfTheData.capacity() - restOfTheData.position()) >= buffer.limit()) {
                    restOfTheData.limit(restOfTheData.position() + buffer.limit());
                    restOfTheData.put(buffer);
                    buffer = restOfTheData.buffer;
                } else {
                    final ByteBufferDesc newBuffer = BuffersStorage.getInstance().getFreeBuffer(restOfTheData.limit() + buffer.limit());
                    restOfTheData.rewind();
                    newBuffer.put(restOfTheData.buffer);
                    newBuffer.put(buffer);
                    buffer = newBuffer.buffer;
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    restOfTheData = newBuffer;
                }
            } else {
                final int len;
                if ((lastPacketLength - restOfTheData.position()) <= buffer.limit()) {
                    len = lastPacketLength - restOfTheData.position();
                } else {
                    len = buffer.limit();
                }
                final int oldLimit = buffer.limit();
                buffer.limit(len);
                restOfTheData.put(buffer);
                buffer.limit(oldLimit);
                if (restOfTheData.position() == lastPacketLength) {
                    if (buffer.hasRemaining()) {
                        parseLaterBuffer = buffer;
                    } else {
                        parseLaterBuffer = null;
                    }
                    buffer = restOfTheData.buffer;
                } else {
                    return;
                }
            }
        }

        buffer.rewind();

        while (buffer.hasRemaining()) {
            if (!hasSomeDataSinceLastConnect) {
                client.setTimeout(CONNECTION_TIMEOUT * 30);
            }
            hasSomeDataSinceLastConnect = true;

            final int currentPacketLength;
            buffer.mark();
            final byte fByte = buffer.get();

            if ((fByte & (1 << 7)) != 0) {
                buffer.reset();
                if (buffer.remaining() < 4) {
                    final ByteBufferDesc reuseLater = restOfTheData;
                    restOfTheData = BuffersStorage.getInstance().getFreeBuffer(16384);
                    restOfTheData.put(buffer);
                    restOfTheData.limit(restOfTheData.position());
                    lastPacketLength = 0;
                    if (reuseLater != null) {
                        BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                    }
                    break;
                }
                buffer.order(ByteOrder.BIG_ENDIAN);
                final int ackId = buffer.getInt() & (~(1 << 31));
                TcpContext.this.callback.onFastConfirm(ackId);
                buffer.order(ByteOrder.LITTLE_ENDIAN);
                continue;
            }

            if (fByte == 0x7f) {
                buffer.reset();
                if (buffer.remaining() < 4) {
                    if ((restOfTheData == null) || ((restOfTheData != null) && (restOfTheData.position() != 0))) {
                        final ByteBufferDesc reuseLater = restOfTheData;
                        restOfTheData = BuffersStorage.getInstance().getFreeBuffer(16384);
                        restOfTheData.put(buffer);
                        restOfTheData.limit(restOfTheData.position());
                        lastPacketLength = 0;
                        if (reuseLater != null) {
                            BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                        }
                    } else {
                        restOfTheData.position(restOfTheData.limit());
                    }
                    break;
                }
                currentPacketLength = (buffer.getInt() >> 8) * 4;
            } else {
                currentPacketLength = ((int) fByte) * 4;
            }

            if (((currentPacketLength % 4) != 0) || (currentPacketLength > MAX_PACKED_SIZE)) {
                Logger.e(TcpContext.this.TAG, "Invalid packet length");
                reconnect();
                return;
            }

            if (currentPacketLength < buffer.remaining()) {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received message len " + currentPacketLength + " but packet larger " + buffer.remaining());
            } else if (currentPacketLength == buffer.remaining()) {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received message len " + currentPacketLength + " equal to packet size");
            } else {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received packet size less(" + buffer.remaining() + ") then message size(" + currentPacketLength + ")");

                ByteBufferDesc reuseLater = null;
                final int len = currentPacketLength + ((fByte == 0x7f) ? 4 : 1);
                if ((restOfTheData != null) && (restOfTheData.capacity() < len)) {
                    reuseLater = restOfTheData;
                    restOfTheData = null;
                }
                if (restOfTheData == null) {
                    buffer.reset();
                    restOfTheData = BuffersStorage.getInstance().getFreeBuffer(len);
                    restOfTheData.put(buffer);
                } else {
                    restOfTheData.position(restOfTheData.limit());
                    restOfTheData.limit(len);
                }
                lastPacketLength = len;
                if (reuseLater != null) {
                    BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                }
                return;
            }

            final ByteBufferDesc toProceed = BuffersStorage.getInstance().getFreeBuffer(currentPacketLength);
            final int old = buffer.limit();
            buffer.limit(buffer.position() + currentPacketLength);
            toProceed.put(buffer);
            buffer.limit(old);
            toProceed.rewind();


            if (toProceed.buffer.remaining() == 4) {
                final int error = toProceed.readInt32(false);
                TcpContext.this.onError(error);
            } else {
                final byte[] pkg = new byte[toProceed.buffer.remaining()];
                toProceed.readRaw(pkg, false);
                onMessage(pkg, currentPacketLength);
                BuffersStorage.getInstance().reuseFreeBuffer(toProceed);
            }

            if (restOfTheData != null) {
                if (((lastPacketLength != 0) && (restOfTheData.position() == lastPacketLength)) || ((lastPacketLength == 0) && !restOfTheData.hasRemaining())) {
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    restOfTheData = null;
                } else {
                    restOfTheData.compact();
                    restOfTheData.limit(restOfTheData.position());
                    restOfTheData.position(0);
                }
            }

            if (parseLaterBuffer != null) {
                buffer = parseLaterBuffer;
                parseLaterBuffer = null;
            }
        }
    }

    public int getContextId() {
        return this.contextId;
    }

    public void postMessage(byte[] data, boolean useFastConfirm) {
        final ByteBufferDesc buffer = BuffersStorage.getInstance().getFreeBuffer(data.length);
        buffer.writeRaw(data);
        sendData(buffer, true, useFastConfirm);
    }

    private synchronized void onMessage(byte[] data, int len) {
        this.callback.onRawMessage(data, 0, len, this);
    }

    private synchronized void onError(int errorCode) {
        this.callback.onError(errorCode, this);
    }

    private void sendData(final ByteBufferDesc buff, final boolean canReuse, final boolean reportAck) {
        if (buff == null) {
            return;
        }
        selector.scheduleTask(() -> {
            if ((connectionState == ConnectionState.TcpConnectionStageIdle) ||
                    (connectionState == ConnectionState.TcpConnectionStageReconnecting) ||
                    (connectionState == ConnectionState.TcpConnectionStageSuspended) || (client == null)) {
                connect();
            }

            if ((client == null) || client.isDisconnected()) {
                if (canReuse) {
                    BuffersStorage.getInstance().reuseFreeBuffer(buff);
                }
                Logger.e(TcpContext.this.TAG, TcpContext.this + " disconnected, don't send data");
                return;
            }

            int bufferLen = buff.limit();
            int packetLength = bufferLen / 4;

            if (packetLength < 0x7f) {
                bufferLen++;
            } else {
                bufferLen += 4;
            }
            if (isFirstPackage) {
                bufferLen++;
            }

            final ByteBufferDesc buffer = BuffersStorage.getInstance().getFreeBuffer(bufferLen);
            if (isFirstPackage) {
                buffer.writeByte((byte) 0xef);
                isFirstPackage = false;
            }
            if (packetLength < 0x7f) {
                if (reportAck) {
                    packetLength |= (1 << 7);
                }
                buffer.writeByte(packetLength);
            } else {
                packetLength = (packetLength << 8) + 0x7f;
                if (reportAck) {
                    packetLength |= (1 << 7);
                }
                buffer.writeInt32(packetLength);
            }

            buffer.writeRaw(buff);
            if (canReuse) {
                BuffersStorage.getInstance().reuseFreeBuffer(buff);
            }

            buffer.rewind();

            TcpContext.this.sentPackets++;
            client.write(buffer);
        });
    }

    //region PyroClient Overrides

    @Override
    public void connectedClient(PyroClient client) {
        connectionState = ConnectionState.TcpConnectionStageConnected;
        channelToken = generateChannelToken();
        Logger.d(TcpContext.this.TAG, "Client connected: " + channelToken);
    }

    @Override
    public void unconnectableClient(PyroClient client, Exception cause) {
        handleDisconnect(client, false);
    }

    @Override
    public void droppedClient(PyroClient client, IOException cause) {
        handleDisconnect(client, (cause instanceof SocketTimeoutException));
    }

    @Override
    public void disconnectedClient(PyroClient client) {
        handleDisconnect(client, false);
    }

    @Override
    public void receivedData(PyroClient client, ByteBuffer data) {
        try {
            failedConnectionCount = 0;
            readData(data);
        } catch (Exception e) {
            Logger.e(TcpContext.this.TAG, e);
            reconnect();
        }
    }

    @Override
    public void sentData(PyroClient client, int bytes) {
        Logger.d(TcpContext.this.TAG, "Received data " + bytes);
    }

    //endregion PyroClient Overrides

    private synchronized void handleDisconnect(PyroClient client, boolean timeout) {
        synchronized (timerSync) {
            if (reconnectTimer != null) {
                reconnectTimer.cancel();
                reconnectTimer = null;
            }
        }

        isFirstPackage = true;
        if (restOfTheData != null) {
            BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
            restOfTheData = null;
        }
        channelToken = 0;
        lastPacketLength = 0;
        if ((connectionState != ConnectionState.TcpConnectionStageSuspended) && (connectionState != ConnectionState.TcpConnectionStageIdle)) {
            connectionState = ConnectionState.TcpConnectionStageIdle;
        }

        callback.onChannelBroken(TcpContext.this);

        if (connectionState == ConnectionState.TcpConnectionStageIdle) {
            failedConnectionCount++;
            if (failedConnectionCount == 1) {
                if (hasSomeDataSinceLastConnect) {
                    willRetryConnectCount = 5;
                } else {
                    willRetryConnectCount = 1;
                }
            }
            Logger.d(TcpContext.this.TAG, "Reconnect " + ip + ":" + port + " " + TcpContext.this);
            try {
                synchronized (timerSync) {
                    reconnectTimer = new Timer();
                    reconnectTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            selector.scheduleTask(() -> {
                                try {
                                    synchronized (timerSync) {
                                        if (reconnectTimer != null) {
                                            reconnectTimer.cancel();
                                            reconnectTimer = null;
                                        }
                                    }
                                } catch (Exception e2) {
                                    Logger.e(TcpContext.this.TAG, e2);
                                }
                                connect();
                            });
                        }
                    }, (failedConnectionCount > 3) ? 500 : 300, (failedConnectionCount > 3) ? 500 : 300);
                }
            } catch (Exception e3) {
                Logger.e(TcpContext.this.TAG, e3);
            }
        }
    }

    public void connect() {
        selector.scheduleTask(() -> {
            if (((connectionState == ConnectionState.TcpConnectionStageConnected) || (connectionState == ConnectionState.TcpConnectionStageConnecting)) && (client != null)) {
                return;
            }

            connectionState = ConnectionState.TcpConnectionStageConnecting;
            try {
                try {
                    synchronized (timerSync) {
                        if (reconnectTimer != null) {
                            reconnectTimer.cancel();
                            reconnectTimer = null;
                        }
                    }
                } catch (Exception e2) {
                    Logger.e(TcpContext.this.TAG, e2);
                }

                Logger.d(TcpContext.this.TAG, String.format(TcpContext.this + " Connecting (%s:%d)", ip, port));
                isFirstPackage = true;
                if (restOfTheData != null) {
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    restOfTheData = null;
                }
                lastPacketLength = 0;
                hasSomeDataSinceLastConnect = false;
                if (client != null) {
                    client.removeListener(TcpContext.this);
                    client.dropConnection();
                    client = null;
                }
                client = selector.connect(new InetSocketAddress(ip, port));
                client.addListener(TcpContext.this);
                client.setTimeout(CONNECTION_TIMEOUT);
                selector.wakeup();
            } catch (Exception e) {
                handleConnectionError(e);
            }
        });
    }

    private void handleConnectionError(Exception e) {
        try {
            synchronized (timerSync) {
                if (reconnectTimer != null) {
                    reconnectTimer.cancel();
                    reconnectTimer = null;
                }
            }
        } catch (Exception e2) {
            Logger.e(TcpContext.this.TAG, e2);
        }
        connectionState = ConnectionState.TcpConnectionStageReconnecting;
        callback.onChannelBroken(TcpContext.this);
        failedConnectionCount++;
        if (failedConnectionCount == 1) {
            if (hasSomeDataSinceLastConnect) {
                willRetryConnectCount = 3;
            } else {
                willRetryConnectCount = 1;
            }
        }
        if (failedConnectionCount > willRetryConnectCount) {
            failedConnectionCount = 0;
        }

        if (e != null) {
            Logger.e(TcpContext.this.TAG, e);
        }
        Logger.d(TcpContext.this.TAG, "Reconnect " + ip + ":" + port + " " + TcpContext.this);
        try {
            reconnectTimer = new Timer();
            reconnectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    selector.scheduleTask(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                synchronized (timerSync) {
                                    if (reconnectTimer != null) {
                                        reconnectTimer.cancel();
                                        reconnectTimer = null;
                                    }
                                }
                            } catch (Exception e2) {
                                Logger.e(TcpContext.this.TAG, e2);
                            }
                            connect();
                        }
                    });
                }
            }, (failedConnectionCount >= 3) ? 500 : 300, (failedConnectionCount >= 3) ? 500 : 300);
        } catch (Exception e3) {
            Logger.e(TcpContext.this.TAG, e3);
        }
    }

    private void reconnect() {
        suspendConnection(false);
        connectionState = ConnectionState.TcpConnectionStageReconnecting;
        connect();
    }

    public void suspendConnection(boolean task) {
        if (task) {
            selector.scheduleTask(new Runnable() {
                @Override
                public void run() {
                    suspendConnectionInternal();
                }
            });
        } else {
            suspendConnectionInternal();
        }
    }

    private void suspendConnectionInternal() {
        synchronized (timerSync) {
            if (reconnectTimer != null) {
                reconnectTimer.cancel();
                reconnectTimer = null;
            }
        }
        if ((connectionState == ConnectionState.TcpConnectionStageIdle) || (connectionState == ConnectionState.TcpConnectionStageSuspended)) {
            return;
        }
        Logger.d(TcpContext.this.TAG, "suspend connnection " + TcpContext.this);
        connectionState = ConnectionState.TcpConnectionStageSuspended;
        if (client != null) {
            client.removeListener(TcpContext.this);
            client.dropConnection();
            client = null;
        }
        callback.onChannelBroken(TcpContext.this);
        isFirstPackage = true;
        if (restOfTheData != null) {
            BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
            restOfTheData = null;
        }
        lastPacketLength = 0;
        channelToken = 0;
    }
}