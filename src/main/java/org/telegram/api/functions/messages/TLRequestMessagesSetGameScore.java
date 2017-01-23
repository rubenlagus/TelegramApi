package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages accept encryption.
 */
public class TLRequestMessagesSetGameScore extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8ef8ecc0;

    private static final int FLAG_EDITMESSAGE    = 0x00000001; // 0
    private static final int FLAG_FORCE          = 0x00000002; // 1

    private int flags;
    private TLAbsInputPeer peer;
    private int id;
    private TLAbsInputUser userId;
    private int score;

    /**
     * Instantiates a new TL request messages accept encryption.
     */
    public TLRequestMessagesSetGameScore() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUpdates))
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void enableEditMessage(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_EDITMESSAGE;
        } else {
            this.flags &= ~FLAG_EDITMESSAGE;
        }
    }

    public void enableForce(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_FORCE;
        } else {
            this.flags &= ~FLAG_FORCE;
        }
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(score, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        id = StreamingUtils.readInt(stream);
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        score = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.setGameScore#8ef8ecc0";
    }
}