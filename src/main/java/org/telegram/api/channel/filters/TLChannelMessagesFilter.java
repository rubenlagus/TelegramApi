package org.telegram.api.channel.filters;

import org.telegram.api.message.TLMessageRange;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel messages filter
 * @date 18 of September of 2015
 */
public class TLChannelMessagesFilter extends TLAbsChannelMessagesFilter {
    public static final int CLASS_ID = 0xcd77d957;

    public static final int FLAG_IMPORTANT_ONLY          = 0x00000001; // 0
    public static final int FLAG_EXCLUDE_NEW_MESSAGES    = 0x00000002; // 1

    private int flags;
    private TLVector<TLMessageRange> ranges;

    public TLChannelMessagesFilter() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public TLVector<TLMessageRange> getRanges() {
        return ranges;
    }

    public void setRanges(TLVector<TLMessageRange> ranges) {
        this.ranges = ranges;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLVector(ranges, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.ranges = (TLVector<TLMessageRange>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "channel.messages.filter.TLChannelMessagesFilter#cd77d957";
    }
}
