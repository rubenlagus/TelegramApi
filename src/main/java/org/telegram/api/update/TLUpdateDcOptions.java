package org.telegram.api.update;

import org.telegram.api.TLDcOption;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update dc options.
 */
public class TLUpdateDcOptions extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e5e9873;

    private TLVector<TLDcOption> dcOptions = new TLVector<>();

    /**
     * Instantiates a new TL update dc options.
     */
    public TLUpdateDcOptions() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets dc options.
     *
     * @return the dc options
     */
    public TLVector getDcOptions() {
        return this.dcOptions;
    }

    /**
     * Sets dc options.
     *
     * @param dcOptions the dc options
     */
    public void setDcOptions(TLVector<TLDcOption> dcOptions) {
        this.dcOptions = dcOptions;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.dcOptions, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.dcOptions = (TLVector<TLDcOption>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "updateDcOptions#8e5e9873";
    }
}