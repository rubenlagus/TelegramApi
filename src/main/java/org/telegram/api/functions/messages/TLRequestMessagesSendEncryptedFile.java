package org.telegram.api.functions.messages;

import org.telegram.api.input.encrypted.TLInputEncryptedChat;
import org.telegram.api.input.encrypted.file.TLAbsInputEncryptedFile;
import org.telegram.api.messages.sentencrypted.TLAbsSentEncryptedMessage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages send encrypted file.
 */
public class TLRequestMessagesSendEncryptedFile extends TLMethod<TLAbsSentEncryptedMessage> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9a901b66;

    private TLInputEncryptedChat peer;
    private long randomId;
    private TLBytes data;
    private TLAbsInputEncryptedFile file;

    /**
     * Instantiates a new TL request messages send encrypted file.
     */
    public TLRequestMessagesSendEncryptedFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsSentEncryptedMessage deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsSentEncryptedMessage))
            return (TLAbsSentEncryptedMessage) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.sentencrypted.TLAbsSentEncryptedMessage, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
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
     * Gets data.
     *
     * @return the data
     */
    public TLBytes getData() {
        return this.data;
    }

    /**
     * Sets data.
     *
     * @param value the value
     */
    public void setData(TLBytes value) {
        this.data = value;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsInputEncryptedFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param value the value
     */
    public void setFile(TLAbsInputEncryptedFile value) {
        this.file = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLBytes(this.data, stream);
        StreamingUtils.writeTLObject(this.file, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLInputEncryptedChat) StreamingUtils.readTLObject(stream, context));
        this.randomId = StreamingUtils.readLong(stream);
        this.data = StreamingUtils.readTLBytes(stream, context);
        this.file = ((TLAbsInputEncryptedFile) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.sendEncryptedFile#9a901b66";
    }
}