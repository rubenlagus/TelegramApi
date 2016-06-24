package org.telegram.api.update;

import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update read messages outbox.
 */
public class TLUpdateReadMessagesOutbox extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2f2f21bf;

    private int pts;
    private int maxId;
    private int ptsCount;
    private TLAbsPeer peer;

    /**
     * Instantiates a new TL update read messages outbox.
     */
    public TLUpdateReadMessagesOutbox() {
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
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public int getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param maxId the max id
     */
    public void setMaxId(int maxId) {
        this.maxId = maxId;
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
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param peer the peer
     */
    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
        this.maxId = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateReadMessageOutbox#2f2f21bf";
    }
}