package org.telegram.api.messages.chats;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chats.
 */
public class TLMessagesChats extends TLAbsMessagesChats {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64ff9fd5;

    /**
     * Instantiates a new TL messages chats.
     */
    public TLMessagesChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }


    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
    }

    public String toString() {
        return "messages.chats#64ff9fd5";
    }
}