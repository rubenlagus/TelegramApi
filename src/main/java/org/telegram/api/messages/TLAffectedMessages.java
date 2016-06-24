package org.telegram.api.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected messages.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAffectedMessages
 * @date 11 of April of 2015
 */
public class TLAffectedMessages extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84d19185;

    private int pts;
    private int ptsCount;

    /**
     * Instantiates a new TL affected messages.
     */
    public TLAffectedMessages() {
        super();
    }

    @Override
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
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.affectedmessages#84d19185";
    }
}
