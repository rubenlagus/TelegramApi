package org.telegram.api.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected history.
 */
public class TLAffectedHistory extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb45c69d1;

    private int pts;
    private int ptsCount;
    private int offset;

    /**
     * Instantiates a new TL affected history.
     */
    public TLAffectedHistory() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param value the value
     */
    public void setPts(int value) {
        this.pts = value;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.offset, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.offset = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.affectedHistory#b45c69d1";
    }
}