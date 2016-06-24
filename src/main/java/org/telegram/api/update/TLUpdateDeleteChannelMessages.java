package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update delete channel messages
 */
public class TLUpdateDeleteChannelMessages extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc37521c9;

    private int channelId;
    private TLIntVector messages;
    private int pts;
    private int ptsCount;

    /**
     * Instantiates a new TL update delete channel messages
     */
    public TLUpdateDeleteChannelMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public TLIntVector getMessages() {
        return messages;
    }

    public void setMessages(TLIntVector messages) {
        this.messages = messages;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.messages = StreamingUtils.readTLIntVector(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "update.TLUpdateDeleteChannelMessages#c37521c9";
    }
}