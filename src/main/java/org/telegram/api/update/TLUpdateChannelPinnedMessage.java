package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new message.
 */
public class TLUpdateChannelPinnedMessage extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x98592475;

    private int channelId;
    private int id;

    /**
     * Instantiates a new TL update new message.
     */
    public TLUpdateChannelPinnedMessage() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateChannelPinnedMessage#98592475";
    }
}