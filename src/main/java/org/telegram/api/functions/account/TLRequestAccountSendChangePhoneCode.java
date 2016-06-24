package org.telegram.api.functions.account;

import org.telegram.api.auth.TLSentCode;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account send change phone code.
 */
public class TLRequestAccountSendChangePhoneCode extends TLMethod<TLSentCode> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e57deb;

    private static final int FLAG_ALLOW_FLASHCALL =  0x00000001; // 0

    private int flags;
    private String phoneNumber;
    private boolean currentNumber;

    public TLRequestAccountSendChangePhoneCode() {
        super();
        currentNumber = false;
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
        throw new IOException("Incorrect response type. Expected " + TLSentCode.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            StreamingUtils.writeTLBool(currentNumber, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.phoneNumber = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            this.currentNumber = StreamingUtils.readTLBool(stream);
        }
    }

    @Override
    public String toString() {
        return "account.sendChangePhoneCode#8e57deb";
    }
}