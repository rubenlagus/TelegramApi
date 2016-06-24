package org.telegram.api.messages;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages slice.
 */
public class TLMessagesSlice extends TLAbsMessages {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb446ae3;

    private int count;

    /**
     * Instantiates a new TL messages slice.
     */
    public TLMessagesSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets count.
     *
     * @param value the value
     */
    public void setCount(int value) {
        this.count = value;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.messages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.messagesSlice#b446ae3";
    }
}