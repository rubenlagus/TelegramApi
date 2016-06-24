package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.keyboard.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages send message.
 */
public class TLRequestMessagesSendMessage extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa88427a;

    private static final int FLAG_REPLY            = 0x00000001; // 0
    private static final int FLAG_NOWEBPREVIEW     = 0x00000002; // 1
    private static final int FLAG_REPLYMARKUP      = 0x00000004; // 2
    private static final int FLAG_ENTITIES         = 0x00000008; // 3
    private static final int FLAG_BROADCAST        = 0x00000010; // 4
    private static final int FLAG_SILENT           = 0x00000020; // 5
    private static final int FLAG_BACKGROUND       = 0x00000040; // 6

    private int flags;
    private TLAbsInputPeer peer;
    private int replyToMsgId;
    private String message;
    private long randomId;
    private TLAbsReplyMarkup replyMarkup;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL request messages send message.
     */
    public TLRequestMessagesSendMessage() {
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

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param value the value
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public long getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param value the value
     */
    public void setRandomId(long value) {
        this.randomId = value;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets reply to msg id.
     *
     * @return the reply to msg id
     */
    public int getReplyToMsgId() {
        return this.replyToMsgId;
    }

    /**
     * Sets reply to msg id.
     *
     * @param replyToMsgId the reply to msg id
     */
    public void setReplyToMsgId(int replyToMsgId) {
        this.flags |= FLAG_REPLY;
        this.replyToMsgId = replyToMsgId;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        this.flags |= FLAG_ENTITIES;
    }

    public void disableWebPreview() {
        this.flags |= FLAG_NOWEBPREVIEW;
    }


    public void enableBroadcast(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_BROADCAST;
        } else {
            this.flags &= ~FLAG_BROADCAST;
        }
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        if ((this.flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        if ((this.flags & FLAG_REPLYMARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        if ((this.flags & FLAG_REPLY) != 0) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.message = StreamingUtils.readTLString(stream);
        this.randomId = StreamingUtils.readLong(stream);
        if ((this.flags & FLAG_REPLYMARKUP) != 0) {
            this.replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }
    }

    public String toString() {
        return "messages.sendMessage#fa88427a";
    }
}