package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountDaysTTL;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account set account tTL.
 */
public class TLRequestAccountSetAccountTTL extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2442485e;

    private TLAccountDaysTTL ttl;

    /**
     * Instantiates a new TL request account set account tTL.
     */
    public TLRequestAccountSetAccountTTL() {
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
     * Gets ttl.
     *
     * @return the ttl
     */
    public TLAccountDaysTTL getTtl() {
        return this.ttl;
    }

    /**
     * Sets ttl.
     *
     * @param ttl the ttl
     */
    public void setTtl(TLAccountDaysTTL ttl) {
        this.ttl = ttl;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.ttl, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.ttl = (TLAccountDaysTTL) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "account.setAccountTTL#2442485e";
    }
}