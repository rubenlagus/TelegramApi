package org.telegram.api.functions.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 08 of August of 2016
 */
public class TLRequestAccountConfirmPhone extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x5f2178c3;

    private String phoneCodeHash;
    private String phoneCode;

    public TLRequestAccountConfirmPhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneCodeHash() {
        return phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(phoneCodeHash, stream);
        StreamingUtils.writeTLString(phoneCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        phoneCodeHash = StreamingUtils.readTLString(stream);
        phoneCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "account.confirmPhone#5f2178c3";
    }
}
