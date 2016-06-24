package org.telegram.api.updates.difference;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference empty.
 */
public class TLDifferenceEmpty extends TLAbsDifference {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5d75a138;

    /**
     * Instantiates a new TL difference empty.
     */
    public TLDifferenceEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.seq, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.date = StreamingUtils.readInt(stream);
        this.seq = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.differenceEmpty#5d75a138";
    }
}