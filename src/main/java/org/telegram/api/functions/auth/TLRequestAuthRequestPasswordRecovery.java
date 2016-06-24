package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLPasswordRecovery;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request auth request password recovery.
 */
public class TLRequestAuthRequestPasswordRecovery extends TLMethod<TLPasswordRecovery> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd897bc66;

    /**
     * Instantiates a new TL request auth request password recovery.
     */
    public TLRequestAuthRequestPasswordRecovery() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLPasswordRecovery deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLPasswordRecovery))
            return (TLPasswordRecovery) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.auth.TLPasswordRecovery, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "account.requestPasswordRecovery#d897bc66";
    }
}