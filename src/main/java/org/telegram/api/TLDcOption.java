package org.telegram.api;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dc option.
 */
public class TLDcOption extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5d8c6cc;

    private static final int FLAG_IPV6         = 0x00000001; // 0
    private static final int FLAG_MEDIA_ONLY   = 0x00000002; // 1
    private static final int FLAG_TCP_ONLY     = 0x00000004; // 2

    private int flags;
    private int id;
    private String ipAddress;
    private int port;

    /**
     * Instantiates a new TL dc option.
     */
    public TLDcOption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(int value) {
        this.id = value;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * Sets ip address.
     *
     * @param value the value
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Sets port.
     *
     * @param value the value
     */
    public void setPort(int value) {
        this.port = value;
    }

    private boolean isIPV6() {
        return (this.flags & FLAG_IPV6) != 0;
    }

    private boolean isMediaOnly() {
        return (this.flags & FLAG_MEDIA_ONLY) != 0;
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