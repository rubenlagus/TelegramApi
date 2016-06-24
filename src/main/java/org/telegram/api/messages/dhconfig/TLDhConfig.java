package org.telegram.api.messages.dhconfig;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dh config.
 */
public class TLDhConfig extends TLAbsDhConfig {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2c221edd;

    /**
     * Instantiates a new TL dh config.
     */
    public TLDhConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.g, stream);
        StreamingUtils.writeTLBytes(this.p, stream);
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeTLBytes(this.random, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.g = StreamingUtils.readInt(stream);
        this.p = StreamingUtils.readTLBytes(stream, context);
        this.version = StreamingUtils.readInt(stream);
        this.random = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "messages.dhConfig#2c221edd";
    }
}