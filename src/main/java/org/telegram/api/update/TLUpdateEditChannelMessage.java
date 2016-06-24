package org.telegram.api.update;

import org.telegram.api.message.TLAbsMessage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new message.
 */
public class TLUpdateEditChannelMessage extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b3f4df7;

    private int pts;
    private int ptsCount;
    private TLAbsMessage message;

    /**
     * Instantiates a new TL update new message.
     */
    public TLUpdateEditChannelMessage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public TLAbsMessage getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param value the value
     */
    public void setMessage(TLAbsMessage value) {
        this.message = value;
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
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
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

    @Override
    public int getChannelId() {
        return message.getChatId();
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.message = ((TLAbsMessage) StreamingUtils.readTLObject(stream, context));
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateEditChannelMessage#1b3f4df7";
    }
}