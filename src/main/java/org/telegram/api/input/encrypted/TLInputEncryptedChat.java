package org.telegram.api.input.encrypted;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input encrypted chat.
 */
public class TLInputEncryptedChat extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf141b5e1;

    private int chatId;
    private long accessHash;

    /**
     * Instantiates a new TL input encrypted chat.
     */
    public TLInputEncryptedChat() {
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
     * @param value the value
     */
    public void setChatId(int value) {
        this.chatId = value;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param value the value
     */
    public void setAccessHash(long value) {
        this.accessHash = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "inputEncryptedChat#f141b5e1";
    }
}