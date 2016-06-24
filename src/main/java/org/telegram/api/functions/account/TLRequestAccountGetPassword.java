package org.telegram.api.functions.account;

import org.telegram.api.account.TLAbsAccountPassword;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request account get password.
 */
public class TLRequestAccountGetPassword extends TLMethod<TLAbsAccountPassword> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x548a30f5;

    /**
     * Instantiates a new TL request account get password.
     */
    public TLRequestAccountGetPassword() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAccountPassword deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsAccountPassword))
            return (TLAbsAccountPassword) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsAccountPassword, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "account.getPassword#548a30f5";
    }
}