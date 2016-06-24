package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountPrivacyRules;
import org.telegram.api.input.privacy.inputprivacykey.TLAbsInputPrivacyKey;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account get privacy.
 */
public class TLRequestAccountGetPrivacy extends TLMethod<TLAccountPrivacyRules> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdadbc950;

    private TLAbsInputPrivacyKey key;

    /**
     * Instantiates a new TL request account get privacy.
     */
    public TLRequestAccountGetPrivacy() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAccountPrivacyRules deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAccountPrivacyRules))
            return (TLAccountPrivacyRules) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAccountPrivacyRules, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public TLAbsInputPrivacyKey getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLAbsInputPrivacyKey key) {
        this.key = key;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.key, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.key = (TLAbsInputPrivacyKey) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "account.getPrivacy#dadbc950";
    }
}