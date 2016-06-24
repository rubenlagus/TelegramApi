package org.telegram.api.messages;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chats.
 */
public class TLMessagesChats extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64ff9fd5;

    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats;

    /**
     * Instantiates a new TL messages chats.
     */
    public TLMessagesChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param value the value
     */
    public void setChats(TLVector<TLAbsChat> value) {
        this.chats = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "messages.chats#64ff9fd5";
    }
}