package org.telegram.api.messages.chats;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chats slice
 */
public class TLMessagesChatsSlice extends TLAbsMessagesChats {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cd81144;

    private int count;

    public TLMessagesChatsSlice() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        count = StreamingUtils.readInt(stream);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
    }

    public String toString() {
        return "messages.chatsSlice#9cd81144";
    }
}