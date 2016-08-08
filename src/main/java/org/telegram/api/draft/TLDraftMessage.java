package org.telegram.api.draft;

import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLDraftMessage extends TLAbsDraftMessage {
    public static final int CLASS_ID = 0xfd8e711f;

    private static final int FLAG_REPLY_TO_MSG_ID = 0x00000001; // 0
    private static final int FLAG_NO_WEBPAGE      = 0x00000002; // 1
    private static final int FLAG_UNUSED_2        = 0x00000004; // 2
    private static final int FLAG_ENTITIES        = 0x00000008; // 3

    private int flags;
    private int replyToMsgId;
    private String message;
    private TLVector<TLAbsMessageEntity> entities;
    private int date;

    public TLDraftMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public String getMessage() {
        return message;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public int getDate() {
        return date;
    }

    public boolean isReply() {
        return (flags & FLAG_REPLY_TO_MSG_ID) != 0;
    }

    public boolean hasWebPreview() {
        return (flags & FLAG_NO_WEBPAGE) == 0;
    }

    public boolean hasEntities() {
        return (flags & FLAG_ENTITIES) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            StreamingUtils.writeInt(replyToMsgId, stream);
        }
        StreamingUtils.writeTLString(message, stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(entities, stream);
        }
        StreamingUtils.writeInt(date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            replyToMsgId = StreamingUtils.readInt(stream);
        }
        message = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
        date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "draftMessage#fd8e711f";
    }
}
