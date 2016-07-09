package org.telegram.mtproto.transport;

/**
 * Created: 13.08.13 15:35
 */
public interface TcpContextCallback {
    void onRawMessage(byte[] data, int offset, int len, TcpContext context);

    void onError(int errorCode, TcpContext context);

    void onChannelBroken(TcpContext context);

    void onFastConfirm(int hash);
}
