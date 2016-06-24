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
 * The type TL request account delete account.
 */
public class TLRequestAccountDeleteAccount extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x418d4e0b;

    /**
     * The Reason.
     */
    protected String reason;

    /**
     * Instantiates a new TL request account delete account.
     */
    public TLRequestAccountDeleteAccount() {
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.reason, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.reason = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "account.deleteAccount#418d4e0b";
    }
}