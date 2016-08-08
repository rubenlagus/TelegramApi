package org.telegram.api.functions.messages;

import org.telegram.api.messages.TLMessagesArchivedStickers;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetArchivedStickers extends TLMethod<TLMessagesArchivedStickers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x906e241f;

    private long offsetId;
    private int limit;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetArchivedStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesArchivedStickers deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesArchivedStickers)) {
            return (TLMessagesArchivedStickers) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesArchivedStickers.class.getName() + ", got: " + res.getClass().getCanonicalName());

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(long offsetId) {
        this.offsetId = offsetId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(offsetId, stream);
        StreamingUtils.writeInt(limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        offsetId = StreamingUtils.readLong(stream);
        limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getArchivedStickers#906e241f";
    }
}