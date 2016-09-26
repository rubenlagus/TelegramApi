package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages forward messages.
 */
public class TLRequestMessagesForwardMessages extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x708e0195;

    private static final int FLAG_UNUSED0           = 0x00000001; // 0
    private static final int FLAG_UNUSED1          = 0x00000002; // 1
    private static final int FLAG_UNUSED2          = 0x00000004; // 2
    private static final int FLAG_UNUSED3          = 0x00000008; // 3
    private static final int FLAG_BROADCAST        = 0x00000010; // 4
    private static final int FLAG_SILENT           = 0x00000020; // 5
    private static final int FLAG_BACKGROUND       = 0x00000040; // 6
    private static final int FLAG_UNUSED7          = 0x00000080; // 7
    private static final int FLAG_WITH_MY_SCORE    = 0x00000100; // 8

    private int flags;
    private TLAbsInputPeer fromPeer;
    private TLIntVector id;
    private TLLongVector randomId;
    private TLAbsInputPeer toPeer;

    /**
     * Instantiates a new TL request messages forward messages.
     */
    public TLRequestMessagesForwardMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public TLAbsInputPeer getFromPeer() {
        return fromPeer;
    }

    public void setFromPeer(TLAbsInputPeer fromPeer) {
        this.fromPeer = fromPeer;
    }

    public TLLongVector getRandomId() {
        return randomId;
    }

    public void setRandomId(TLLongVector randomId) {
        this.randomId = randomId;
    }

    public TLAbsInputPeer getToPeer() {
        return toPeer;
    }

    public void setToPeer(TLAbsInputPeer toPeer) {
        this.toPeer = toPeer;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLIntVector getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLIntVector value) {
        this.id = value;
    }

    public void enableBroadcast(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_BROADCAST;
        } else {
            this.flags &= ~FLAG_BROADCAST;
        }
    }

    public void enableSilent(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_SILENT;
        } else {
            this.flags &= ~FLAG_SILENT;
        }
    }

    public void enableBackground(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_BACKGROUND;
        } else {
            this.flags &= ~FLAG_BACKGROUND;
        }
    }

    public void enableWithMyScore(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_WITH_MY_SCORE;
        } else {
            this.flags &= ~FLAG_WITH_MY_SCORE;
        }
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.fromPeer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
        StreamingUtils.writeTLVector(this.randomId, stream);
        StreamingUtils.writeTLObject(this.toPeer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.fromPeer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        this.id = StreamingUtils.readTLIntVector(stream, context);
        this.randomId = StreamingUtils.readTLLongVector(stream, context);
        this.toPeer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.forwardMessages#708e0195";
    }
}