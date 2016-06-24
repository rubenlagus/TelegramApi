package org.telegram.api.input.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input file big.
 */
public class TLInputFileBig extends TLAbsInputFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa4f0bb5;

    /**
     * Instantiates a new TL input file big.
     */
    public TLInputFileBig() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeTLString(this.name, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.name = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputFileBig#fa4f0bb5";
    }
}