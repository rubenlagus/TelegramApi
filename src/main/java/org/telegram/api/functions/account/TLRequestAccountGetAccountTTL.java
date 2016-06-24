package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountDaysTTL;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request account get account tTL.
 */
public class TLRequestAccountGetAccountTTL extends TLMethod<TLAccountDaysTTL> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8fc711d;

    /**
     * Instantiates a new TL request account get account tTL.
     */
    public TLRequestAccountGetAccountTTL() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAccountDaysTTL deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAccountDaysTTL))
            return (TLAccountDaysTTL) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "account.getAccountTTL#8fc711d";
    }
}