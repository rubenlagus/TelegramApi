package org.telegram.api.phone;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneConnection extends TLObject {
    public static final int CLASS_ID = 0x9d4c17c0;

    private long id;
    private String ip;
    private String ipv6;
    private int port;
    private TLBytes peerTag;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getIp() {
        return ip;
    }

    public String getIpv6() {
        return ipv6;
    }

    public int getPort() {
        return port;
    }

    public TLBytes getPeerTag() {
        return peerTag;
    }


    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeTLString(ip, stream);
        StreamingUtils.writeTLString(ipv6, stream);
        StreamingUtils.writeInt(port, stream);
        StreamingUtils.writeTLBytes(peerTag, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
        ip = StreamingUtils.readTLString(stream);
        ipv6 = StreamingUtils.readTLString(stream);
        port = StreamingUtils.readInt(stream);
        peerTag = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "phoneConnection#9d4c17c0";
    }
}
