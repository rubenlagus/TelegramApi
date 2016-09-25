package org.telegram.api.updates.channel.differences;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL updates channel differences empty.
 */
public class TLUpdatesChannelDifferencesEmpty extends TLAbsUpdatesChannelDifferences {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3e11affb;

    private static final int FLAG_FINAL = 0x00000001; // 0
    private static final int FLAG_TIMEOUT = 0x00000002; // 1

    /**
     * Instantiates a new TL updates channel differences empty.
     */
    public TLUpdatesChannelDifferencesEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isFinal() {
        return (flags & FLAG_FINAL) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            StreamingUtils.writeInt(timeout, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            this.timeout = StreamingUtils.readInt(stream);
        }
    }

    public String toString() {
        return "updates.channelDifferenceEmpty#3e11affb";
    }
}