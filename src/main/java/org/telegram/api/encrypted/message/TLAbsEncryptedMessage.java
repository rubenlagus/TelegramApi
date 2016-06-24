package org.telegram.api.encrypted.message;

import org.telegram.api.encrypted.file.TLAbsEncryptedFile;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLObject;

/**
 * The type TL abs encrypted message.
 */
public abstract class TLAbsEncryptedMessage extends TLObject {
    /**
     * The Random id.
     */
    protected long randomId;
    /**
     * The Chat id.
     */
    protected int chatId;
    /**
     * The Date.
     */
    protected int date;
    /**
     * The Bytes.
     */
    protected TLBytes bytes;
    /**
     * The File.
     */
    protected TLAbsEncryptedFile file;

    /**
     * Instantiates a new TL abs encrypted message.
     */
    protected TLAbsEncryptedMessage() {
        super();
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public long getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param value the value
     */
    public void setRandomId(long value) {
        this.randomId = value;
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
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    /**
     * Gets bytes.
     *
     * @return the bytes
     */
    public TLBytes getBytes() {
        return this.bytes;
    }

    /**
     * Sets bytes.
     *
     * @param value the value
     */
    public void setBytes(TLBytes value) {
        this.bytes = value;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsEncryptedFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param file the file
     */
    public void setFile(TLAbsEncryptedFile file) {
        this.file = file;
    }
}