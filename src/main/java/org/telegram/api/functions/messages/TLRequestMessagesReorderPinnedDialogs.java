package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesReorderPinnedDialogs extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x959ff644;

    private static final int FLAG_FORCE   = 0x00000001; // 0

    private int flags;
    private TLVector<TLAbsInputPeer> order;

    /**
     * Instantiates a new TL request messages get dialogs.
     */
    public TLRequestMessagesReorderPinnedDialogs() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLVector<TLAbsInputPeer> getOrder() {
        return order;
    }

    public void setOrder(TLVector<TLAbsInputPeer> order) {
        this.order = order;
    }

    private void setForce(boolean force) {
        if (force) {
            this.flags |= FLAG_FORCE;
        } else {
            this.flags &= ~FLAG_FORCE;
        }
    }

    private boolean isForced() {
        return (flags & FLAG_FORCE) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.order, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.order = StreamingUtils.readTLVector(stream, context, TLAbsInputPeer.class);
    }

    public String toString() {
        return "messages.reorderPinnedDialogs#959ff644";
    }
}