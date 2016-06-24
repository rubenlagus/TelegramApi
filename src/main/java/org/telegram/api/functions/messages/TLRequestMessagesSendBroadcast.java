package org.telegram.api.functions.messages;

import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages send broadcast.
 */
public class TLRequestMessagesSendBroadcast extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf73f4da;

    private TLVector<TLAbsInputUser> contacts;
    private String message;
    private TLAbsInputMedia media;
    private TLLongVector randomId;

    /**
     * Instantiates a new TL request messages send broadcast.
     */
    public TLRequestMessagesSendBroadcast() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUpdates))
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets contacts.
     *
     * @return the contacts
     */
    public TLVector<TLAbsInputUser> getContacts() {
        return this.contacts;
    }

    /**
     * Sets contacts.
     *
     * @param value the value
     */
    public void setContacts(TLVector<TLAbsInputUser> value) {
        this.contacts = value;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param value the value
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public TLAbsInputMedia getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param value the value
     */
    public void setMedia(TLAbsInputMedia value) {
        this.media = value;
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public TLLongVector getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param randomId the random id
     */
    public void setRandomId(TLLongVector randomId) {
        this.randomId = randomId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.contacts, stream);
        StreamingUtils.writeTLVector(this.randomId, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLObject(this.media, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.contacts = StreamingUtils.readTLVector(stream, context);
        this.randomId = StreamingUtils.readTLLongVector(stream, context);
        this.message = StreamingUtils.readTLString(stream);
        this.media = ((TLAbsInputMedia) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.sendBroadcast#bf73f4da";
    }
}