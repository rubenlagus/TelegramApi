package org.telegram.api.functions.updates;

import org.telegram.api.updates.difference.TLAbsDifference;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request updates get difference.
 */
public class TLRequestUpdatesGetDifference extends TLMethod<TLAbsDifference> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa041495;

    private int pts;
    private int date;
    private int qts;

    /**
     * Instantiates a new TL request updates get difference.
     */
    public TLRequestUpdatesGetDifference() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsDifference deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsDifference))
            return (TLAbsDifference) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.difference.TLAbsDifference, got: " + res.getClass().getCanonicalName());
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
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    /**
     * Gets qts.
     *
     * @return the qts
     */
    public int getQts() {
        return this.qts;
    }

    /**
     * Sets qts.
     *
     * @param value the value
     */
    public void setQts(int value) {
        this.qts = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.qts, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.pts = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.qts = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.getDifference#a041495";
    }
}
