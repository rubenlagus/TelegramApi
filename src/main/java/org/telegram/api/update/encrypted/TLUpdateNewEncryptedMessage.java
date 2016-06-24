package org.telegram.api.update.encrypted;

import org.telegram.api.encrypted.message.TLAbsEncryptedMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new encrypted message.
 */
public class TLUpdateNewEncryptedMessage extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12bcbd9a;

    private int qts;
    private TLAbsEncryptedMessage message;

    /**
     * Instantiates a new TL update new encrypted message.
     */
    public TLUpdateNewEncryptedMessage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets qts.
     *
     * @return the qts
     */
    public int getQts() {
        return this.qts;
    }

    /**
     * Sets qts.
     *
     * @param qts the qts
     */
    public void setQts(int qts) {
        this.qts = qts;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public TLAbsEncryptedMessage getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(TLAbsEncryptedMessage message) {
        this.message = message;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
        StreamingUtils.writeInt(this.qts, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.message = (TLAbsEncryptedMessage) StreamingUtils.readTLObject(stream, context);
        this.qts = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateNewEncryptedMessage#12bcbd9a";
    }
}