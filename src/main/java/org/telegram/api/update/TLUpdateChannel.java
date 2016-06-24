package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel
 */
public class TLUpdateChannel extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb6d45656;

    private int channelId;

    /**
     * Instantiates a new TL update channel
     */
    public TLUpdateChannel() {
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "update.TLUpdateChannel#b6d45656";
    }
}