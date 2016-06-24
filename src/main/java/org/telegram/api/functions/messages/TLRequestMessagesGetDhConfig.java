package org.telegram.api.functions.messages;

import org.telegram.api.messages.dhconfig.TLAbsDhConfig;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get dh config.
 */
public class TLRequestMessagesGetDhConfig extends TLMethod<TLAbsDhConfig> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x26cf8950;

    private int version;
    private int randomLength;

    /**
     * Instantiates a new TL request messages get dh config.
     */
    public TLRequestMessagesGetDhConfig() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsDhConfig deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsDhConfig))
            return (TLAbsDhConfig) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.dhconfig.TLAbsDhConfig, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param value the value
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Gets random length.
     *
     * @return the random length
     */
    public int getRandomLength() {
        return this.randomLength;
    }

    /**
     * Sets random length.
     *
     * @param value the value
     */
    public void setRandomLength(int value) {
        this.randomLength = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeInt(this.randomLength, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.version = StreamingUtils.readInt(stream);
        this.randomLength = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getDhConfig#26cf8950";
    }
}