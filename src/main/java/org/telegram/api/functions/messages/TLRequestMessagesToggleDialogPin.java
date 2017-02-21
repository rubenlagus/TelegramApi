package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesToggleDialogPin extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x3289be6a;

    private static final int FLAG_PINNED      = 0x00000001; // 0

    private int flags;
    private TLAbsInputPeer peer;

    /**
     * Instantiates a new TL request messages get dialogs.
     */
    public TLRequestMessagesToggleDialogPin() {
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

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    private void setPinned(boolean pinned) {
        if (pinned) {
            this.flags |= FLAG_PINNED;
        } else {
            this.flags &= ~FLAG_PINNED;
        }
    }

    private boolean isPinned() {
        return (flags & FLAG_PINNED) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    public String toString() {
        return "messages.toggleDialogPin#3289be6a";
    }
}