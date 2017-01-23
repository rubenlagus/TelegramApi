package org.telegram.api.updates.difference;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference too long.
 */
public class TLDifferenceTooLong extends TLAbsDifference {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4afe8f6d;

    private int pts;

    public TLDifferenceTooLong() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPts() {
        return pts;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(pts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.pts = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updates.differenceTooLong#4afe8f6d";
    }
}