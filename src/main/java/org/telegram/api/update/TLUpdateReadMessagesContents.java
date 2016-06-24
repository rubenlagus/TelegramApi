package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update read messages contents.
 */
public class TLUpdateReadMessagesContents extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x68c13933;

    private TLIntVector messages;
    private int pts;
    private int ptsCount;

    /**
     * Instantiates a new TL update read messages contents.
     */
    public TLUpdateReadMessagesContents() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public TLIntVector getMessages() {
        return this.messages;
    }

    /**
     * Sets messages.
     *
     * @param messages the messages
     */
    public void setMessages(TLIntVector messages) {
        this.messages = messages;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.messages = StreamingUtils.readTLIntVector(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.updateReadMessagesContents#68c13933";
    }
}