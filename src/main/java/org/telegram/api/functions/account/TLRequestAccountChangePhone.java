package org.telegram.api.functions.account;

import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account change phone.
 */
public class TLRequestAccountChangePhone extends TLMethod<TLAbsUser> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x70c32edb;

    private String phoneNumber;
    private String phoneCodeHash;
    private String phoneCode;

    /**
     * Instantiates a new TL request account change phone.
     */
    public TLRequestAccountChangePhone() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUser deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUser))
            return (TLAbsUser) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsUser, got: " + res.getClass().getCanonicalName());
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
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone code.
     *
     * @return the phone code
     */
    public String getPhoneCode() {
        return this.phoneCode;
    }

    /**
     * Sets phone code.
     *
     * @param phoneCode the phone code
     */
    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    /**
     * Gets phone code hash.
     *
     * @return the phone code hash
     */
    public String getPhoneCodeHash() {
        return this.phoneCodeHash;
    }

    /**
     * Sets phone code hash.
     *
     * @param phoneCodeHash the phone code hash
     */
    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLString(this.phoneCodeHash, stream);
        StreamingUtils.writeTLString(this.phoneCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.phoneCodeHash = StreamingUtils.readTLString(stream);
        this.phoneCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "account.sendChangePhone#70c32edb";
    }
}