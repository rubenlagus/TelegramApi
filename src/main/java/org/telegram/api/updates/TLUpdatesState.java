package org.telegram.api.updates;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL updates state.
 */
public class TLUpdatesState extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa56c2a3e;

    private int pts;
    private int qts;
    private int date;
    private int seq;
    private int unreadCount;

    /**
     * Instantiates a new TL updates state.
     */
    public TLUpdatesState() {
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
     * Gets seq.
     *
     * @return the seq
     */
    public int getSeq() {
        return this.seq;
    }

    /**
     * Sets seq.
     *
     * @param value the value
     */
    public void setSeq(int value) {
        this.seq = value;
    }

    /**
     * Gets unread count.
     *
     * @return the unread count
     */
    public int getUnreadCount() {
        return this.unreadCount;
    }

    /**
     * Sets unread count.
     *
     * @param value the value
     */
    public void setUnreadCount(int value) {
        this.unreadCount = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.qts, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.seq, stream);
        StreamingUtils.writeInt(this.unreadCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.pts = StreamingUtils.readInt(stream);
        this.qts = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.seq = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.state#a56c2a3e";
    }
}