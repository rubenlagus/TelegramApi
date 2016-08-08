package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesSaveDraft extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc39e14b;

    private static final int FLAG_REPLY_TO_MSG_ID = 0x00000001; // 0
    private static final int FLAG_NO_WEBPAGE      = 0x00000002; // 1
    private static final int FLAG_UNUSED_2        = 0x00000004; // 2
    private static final int FLAG_ENTITIES        = 0x00000008; // 3

    private int flags;
    private int replyToMsgId;
    private TLAbsInputPeer peer;
    private String message;
    private TLVector<TLAbsMessageEntity> entitites;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesSaveDraft() {
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

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        flags |= FLAG_REPLY_TO_MSG_ID;
        this.replyToMsgId = replyToMsgId;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TLVector<TLAbsMessageEntity> getEntitites() {
        return entitites;
    }

    public void setEntitites(TLVector<TLAbsMessageEntity> entitites) {
        flags |= FLAG_ENTITIES;
        this.entitites = entitites;
    }

    public boolean hasWebPreview() {
        return (flags & FLAG_NO_WEBPAGE) == 0;
    }

    public void disableWebPreview() {
        flags |= FLAG_NO_WEBPAGE;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            StreamingUtils.writeInt(replyToMsgId, stream);
        }
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLString(message, stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(entitites, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            replyToMsgId = StreamingUtils.readInt(stream);
        }
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        message = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            entitites = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    public String toString() {
        return "messages.saveDraft#bc39e14b";
    }
}