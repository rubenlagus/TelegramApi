package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.messages.TLMessagesHighScores;
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
public class TLRequestMessagesGetGameHighScores extends TLMethod<TLMessagesHighScores> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe822649d;

    private TLAbsInputPeer peer;
    private int id;
    private TLAbsInputUser userId;

    /**
     * Instantiates a new TL request messages accept encryption.
     */
    public TLRequestMessagesGetGameHighScores() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesHighScores deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLMessagesHighScores))
            return (TLMessagesHighScores) res;
        throw new IOException("Incorrect response type. Expected " + TLMessagesHighScores.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        id = StreamingUtils.readInt(stream);
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    public String toString() {
        return "messages.getGameHighScores#e822649d";
    }
}