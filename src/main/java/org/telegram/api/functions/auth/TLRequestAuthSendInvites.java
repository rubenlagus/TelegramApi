package org.telegram.api.functions.auth;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLStringVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth send invites.
 */
public class TLRequestAuthSendInvites extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x771c1d97;

    private TLStringVector phoneNumbers;
    private String message;

    /**
     * Instantiates a new TL request auth send invites.
     */
    public TLRequestAuthSendInvites() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets phone numbers.
     *
     * @return the phone numbers
     */
    public TLStringVector getPhoneNumbers() {
        return this.phoneNumbers;
    }

    /**
     * Sets phone numbers.
     *
     * @param value the value
     */
    public void setPhoneNumbers(TLStringVector value) {
        this.phoneNumbers = value;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.phoneNumbers, stream);
        StreamingUtils.writeTLString(this.message, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.phoneNumbers = StreamingUtils.readTLStringVector(stream, context);
        this.message = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "auth.sendInvites#771c1d97";
    }
}