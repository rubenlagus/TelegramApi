package org.telegram.api;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLDcOption extends TLObject {
    public static final int CLASS_ID = 0x5d8c6cc;

    private static final int FLAG_IPV6         = 0x00000001; // 0
    private static final int FLAG_MEDIA_ONLY   = 0x00000002; // 1
    private static final int FLAG_TCP_ONLY     = 0x00000004; // 2
    private static final int FLAG_CDN          = 0x00000008; // 3

    private int flags;
    private int id;
    private String ipAddress;
    private int port;

    public TLDcOption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getId() {
        return this.id;
    }

    public int getFlags() {
        return this.flags;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    public boolean isIPV6() {
        return (this.flags & FLAG_IPV6) != 0;
    }

    public boolean isMediaOnly() {
        return (this.flags & FLAG_MEDIA_ONLY) != 0;
    }

    public boolean isTcpOnly() {
        return (this.flags & FLAG_TCP_ONLY) != 0;
    }

    public boolean isCdn() {
        return (this.flags & FLAG_CDN) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.ipAddress, stream);
        StreamingUtils.writeInt(this.port, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.ipAddress = StreamingUtils.readTLString(stream);
        this.port = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "dcOption#5d8c6cc";
    }
}