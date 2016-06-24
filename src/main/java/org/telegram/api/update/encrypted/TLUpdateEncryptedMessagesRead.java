package org.telegram.api.update.encrypted;

import org.telegram.api.update.TLAbsUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update encrypted messages read.
 */
public class TLUpdateEncryptedMessagesRead extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x38fe25b7;

    private int chatId;
    private int maxDate;
    private int date;

    /**
     * Instantiates a new TL update encrypted messages read.
     */
    public TLUpdateEncryptedMessagesRead() {
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

    /**
     * Gets max date.
     *
     * @return the max date
     */
    public int getMaxDate() {

        return this.maxDate;
    }

    /**
     * Sets max date.
     *
     * @param maxDate the max date
     */
    public void setMaxDate(int maxDate) {
        this.maxDate = maxDate;
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
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.maxDate, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.maxDate = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateEncryptedMessagesRead#38fe25b7";
    }
}