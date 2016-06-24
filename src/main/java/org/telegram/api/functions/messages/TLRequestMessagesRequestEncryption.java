package org.telegram.api.functions.messages;

import org.telegram.api.encrypted.chat.TLAbsEncryptedChat;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages request encryption.
 */
public class TLRequestMessagesRequestEncryption extends TLMethod<TLAbsEncryptedChat> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf64daf43;

    private TLAbsInputUser userId;
    private int randomId;
    private TLBytes gA;

    /**
     * Instantiates a new TL request messages request encryption.
     */
    public TLRequestMessagesRequestEncryption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsEncryptedChat deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsEncryptedChat))
            return (TLAbsEncryptedChat) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.encrypted.chat.TLAbsEncryptedChat, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public int getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param value the value
     */
    public void setRandomId(int value) {
        this.randomId = value;
    }

    /**
     * Gets gA.
     *
     * @return the gA
     */
    public TLBytes getGA() {
        return this.gA;
    }

    /**
     * Sets gA.
     *
     * @param value the value
     */
    public void setGA(TLBytes value) {
        this.gA = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.randomId, stream);
        StreamingUtils.writeTLBytes(this.gA, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
        this.randomId = StreamingUtils.readInt(stream);
        this.gA = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "messages.requestEncryption#f64daf43";
    }
}