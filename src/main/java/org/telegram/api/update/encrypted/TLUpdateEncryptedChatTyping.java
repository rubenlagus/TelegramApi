package org.telegram.api.update.encrypted;

import org.telegram.api.update.TLAbsUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update encrypted chat typing.
 */
public class TLUpdateEncryptedChatTyping extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1710f156;
    private int chatId;

    /**
     * Instantiates a new TL update encrypted chat typing.
     */
    public TLUpdateEncryptedChatTyping() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return this.chatId;
    }

    /**
     * Sets chat id.
     *
     * @param chatId the chat id
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateEncryptedChatTyping#1710f156";
    }
}