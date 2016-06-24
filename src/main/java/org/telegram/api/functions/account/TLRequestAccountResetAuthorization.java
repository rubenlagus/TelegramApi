package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountPrivacyRules;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account reset authorization.
 */
public class TLRequestAccountResetAuthorization extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdf77f3bc;

    private long hash;

    /**
     * Instantiates a new TL request account reset authorization.
     */
    public TLRequestAccountResetAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public long getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(long hash) {
        this.hash = hash;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAccountPrivacyRules))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.privacy.TLBool, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "account.resetAuthorization#df77f3bc";
    }
}