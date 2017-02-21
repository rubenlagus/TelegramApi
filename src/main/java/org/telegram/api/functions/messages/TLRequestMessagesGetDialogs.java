package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.dialogs.TLAbsDialogs;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesGetDialogs extends TLMethod<TLAbsDialogs> {
    public static final int CLASS_ID = 0x191ba9c5;

    private static final int FLAG_EXCLUDE_PINNED      = 0x00000001; // 0

    private int flags;
    private int offsetDate;
    private int offsetId;
    private TLAbsInputPeer offsetPeer;
    private int limit;

    /**
     * Instantiates a new TL request messages get dialogs.
     */
    public TLRequestMessagesGetDialogs() {
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
        throw new IOException("Incorrect response type. Expected " + TLAbsDialogs.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getOffsetDate() {
        return offsetDate;
    }

    public void setOffsetDate(int offsetDate) {
        this.offsetDate = offsetDate;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public TLAbsInputPeer getOffsetPeer() {
        return offsetPeer;
    }

    public void setOffsetPeer(TLAbsInputPeer offsetPeer) {
        this.offsetPeer = offsetPeer;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    private void excludePinned(boolean excluded) {
        if (excluded) {
            this.flags |= FLAG_EXCLUDE_PINNED;
        } else {
            this.flags &= ~FLAG_EXCLUDE_PINNED;
        }
    }

    private boolean pinnedExcluded() {
        return (flags & FLAG_EXCLUDE_PINNED) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.offsetDate, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeTLObject(this.offsetPeer, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.offsetDate = StreamingUtils.readInt(stream);
        this.offsetId = StreamingUtils.readInt(stream);
        this.offsetPeer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getDialogs#191ba9c5";
    }
}