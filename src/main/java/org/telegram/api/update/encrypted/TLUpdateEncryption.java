package org.telegram.api.update.encrypted;

import org.telegram.api.encrypted.chat.TLAbsEncryptedChat;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update encryption.
 */
public class TLUpdateEncryption extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb4a2e88d;

    private TLAbsEncryptedChat chat;
    private int date;

    /**
     * Instantiates a new TL update encryption.
     */
    public TLUpdateEncryption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat.
     *
     * @return the chat
     */
    public TLAbsEncryptedChat getChat() {
        return this.chat;
    }

    /**
     * Sets chat.
     *
     * @param chat the chat
     */
    public void setChat(TLAbsEncryptedChat chat) {
        this.chat = chat;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.chat, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chat = (TLAbsEncryptedChat) StreamingUtils.readTLObject(stream, context);
        this.date = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateEncryption#b4a2e88d";
    }
}