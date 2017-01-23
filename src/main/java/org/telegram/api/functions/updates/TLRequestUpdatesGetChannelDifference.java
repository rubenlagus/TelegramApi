package org.telegram.api.functions.updates;

import org.telegram.api.channel.filters.TLAbsChannelMessagesFilter;
import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.updates.channel.differences.TLAbsUpdatesChannelDifferences;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestUpdatesGetChannelDifference extends TLMethod<TLAbsUpdatesChannelDifferences> {
    public static final int CLASS_ID = 0x3173d78;

    private static final int FLAG_FORCE    = 0x00000001; // 0

    private int flags;
    private TLAbsInputChannel channel;
    private TLAbsChannelMessagesFilter filter;
    private int pts;
    private int limit;

    public TLRequestUpdatesGetChannelDifference() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdatesChannelDifferences deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdatesChannelDifferences)) {
            return (TLAbsUpdatesChannelDifferences) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdatesChannelDifferences.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getFlags() {
        return flags;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsChannelMessagesFilter getFilter() {
        return filter;
    }

    public void setFilter(TLAbsChannelMessagesFilter filter) {
        this.filter = filter;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setForce(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_FORCE;
        } else {
            this.flags &= ~FLAG_FORCE;
        }
    }


    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.filter, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.filter = StreamingUtils.readTLObject(stream, context, TLAbsChannelMessagesFilter.class);
        this.pts = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.getChannelDifference#3173d78";
    }
}
