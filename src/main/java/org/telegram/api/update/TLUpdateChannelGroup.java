package org.telegram.api.update;

import org.telegram.api.message.TLMessageGroup;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateChannelGroup extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc36c1e3c;

    private int channelId;
    private TLMessageGroup group;

    /**
     * Instantiates a new TL update channel group
     */
    public TLUpdateChannelGroup() {
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

    public TLMessageGroup getGroup() {
        return group;
    }

    public void setGroup(TLMessageGroup group) {
        this.group = group;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeTLObject(this.group, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.group = (TLMessageGroup) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "update.TLUpdateChannelGroup#c36c1e3c";
    }
}