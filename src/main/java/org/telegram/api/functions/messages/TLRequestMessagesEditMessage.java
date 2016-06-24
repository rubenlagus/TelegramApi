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
 * The type TL request channel edit admin
 */
public class TLRequestMessagesEditMessage extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xce91e4ca;

    private static final int FLAG_UNUSED_0       = 0x00000001; // 0
    private static final int FLAG_NO_WEBPREVIEW  = 0x00000002; // 1
    private static final int FLAG_REPLY_MARKUP   = 0x00000004; // 2
    private static final int FLAG_ENTITIES       = 0x00000008; // 3
    private static final int FLAG_UNUSED_4       = 0x00000010; // 4
    private static final int FLAG_UNUSED_5       = 0x00000020; // 5
    private static final int FLAG_UNUSED_6       = 0x00000040; // 6
    private static final int FLAG_UNUSED_7       = 0x00000080; // 7
    private static final int FLAG_UNUSED_8       = 0x00000100; // 8
    private static final int FLAG_UNUSED_9       = 0x00000200; // 9
    private static final int FLAG_UNUSED_10      = 0x00000400; // 10
    private static final int FLAG_MESSAGE        = 0x00000800; // 11

    private int flags;
    private TLAbsInputPeer peer;
    private int id;
    private String message;
    private TLAbsReplyMarkup replyMarkup;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestMessagesEditMessage() {
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
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.id, stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeTLString(message, stream);
        }
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(replyMarkup, stream);
        }
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.id = StreamingUtils.readInt(stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            message = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
        if ((flags & FLAG_ENTITIES) != 0) {
            entities = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }

    }

    public String toString() {
        return "messages.editMessage#ce91e4ca";
    }
}