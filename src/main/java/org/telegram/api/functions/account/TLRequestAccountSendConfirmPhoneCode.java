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
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 08 of August of 2016
 */
public class TLRequestAccountSendConfirmPhoneCode extends TLMethod<TLSentCode> {
    public static final int CLASS_ID = 0x1516d7bd;

    private static final int FLAG_ALLOW_FLASHCALL = 0x00000001; // 0

    private int flags;
    private String hash;
    private boolean currentNumber;

    public TLRequestAccountSendConfirmPhoneCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(boolean currentNumber) {
        flags |= FLAG_ALLOW_FLASHCALL;
        this.currentNumber = currentNumber;
    }

    public boolean allowFlashCalls() {
        return (flags & FLAG_ALLOW_FLASHCALL) != 0;
    }

    @Override
    public TLSentCode deserializeResponse(InputStream stream, TLContext context) throws IOException {
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
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(hash, stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            StreamingUtils.writeTLBool(currentNumber, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        hash = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ALLOW_FLASHCALL) != 0) {
            currentNumber = StreamingUtils.readTLBool(stream);
        }
    }

    @Override
    public String toString() {
        return "account.sendConfirmPhoneCode#1516d7bd";
    }
}
