package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLSentCode;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth send code.
 */
public class TLRequestAuthSendCode extends TLMethod<TLSentCode> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x86aef0ec;

    private static final int FLAG_ALLOW_FLASHCALL = 0x00000001; // 0

    private int flags;
    private String phoneNumber;
    private Boolean currentPhoneNumber;
    private int apiId;
    private String apiHash;

    /**
     * Instantiates a new TL request auth send code.
     */
    public TLRequestAuthSendCode() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLSentCode deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLSentCode)) {
            return (TLSentCode) res;
        }
        throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLSentCode, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param value the value
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets api id.
     *
     * @return the api id
     */
    public int getApiId() {
        return this.apiId;
    }

    /**
     * Sets api id.
     *
     * @param value the value
     */
    public void setApiId(int value) {
        this.apiId = value;
    }

    /**
     * Gets api hash.
     *
     * @return the api hash
     */
    public String getApiHash() {
        return this.apiHash;
    }

    /**
     * Sets api hash.
     *
     * @param value the value
     */
    public void setApiHash(String value) {
        this.apiHash = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            StreamingUtils.writeTLBool(this.currentPhoneNumber, stream);
        }
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.apiHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.phoneNumber = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            this.currentPhoneNumber = StreamingUtils.readTLBool(stream);
        }
        this.apiId = StreamingUtils.readInt(stream);
        this.apiHash = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "auth.sendCode#86aef0ec";
    }
}