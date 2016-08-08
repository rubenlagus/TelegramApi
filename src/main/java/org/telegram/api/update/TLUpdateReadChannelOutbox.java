package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel new message
 */
public class TLUpdateReadChannelOutbox extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x25d6c9c7;

    private int channelId;
    private int maxId;

    /**
     * Instantiates a new TL update channel new message
     */
    public TLUpdateReadChannelOutbox() {
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

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateReadChannelOutbox#25d6c9c7";
    }
}