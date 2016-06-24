package org.telegram.api.functions.channels;

import org.telegram.api.messages.dialogs.TLAbsDialogs;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get channel dialogs.
 */
public class TLRequestChannelsGetDialogs extends TLMethod<TLAbsDialogs> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa9d3d249;

    private int offset;
    private int limit;

    /**
     * Instantiates a new TL request messages get channel dialogs.
     */
    public TLRequestChannelsGetDialogs() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsDialogs deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsDialogs)) {
            return (TLAbsDialogs) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsDialogs.class.getName() +", got: " + res.getClass().getCanonicalName());
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "functions.channels.TLRequestChannelsGetDialogs#a9d3d249";
    }
}