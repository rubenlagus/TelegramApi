package org.telegram.api.messages.dhconfig;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dh config not modified.
 */
public class TLDhConfigNotModified extends TLAbsDhConfig {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc0e24635;

    /**
     * Instantiates a new TL dh config not modified.
     */
    public TLDhConfigNotModified() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.random, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.random = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "messages.dhConfigNotModified#c0e24635";
    }
}