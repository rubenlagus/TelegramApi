package org.telegram.api.update;

import org.telegram.api.privacy.privacykey.TLAbsPrivacyKey;
import org.telegram.api.privacy.privacyrule.TLAbsPrivacyRule;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update privacy.
 */
public class TLUpdatePrivacy extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee3b272a;

    private TLAbsPrivacyKey key;
    private TLVector<TLAbsPrivacyRule> rules;

    /**
     * Instantiates a new TL update privacy.
     */
    public TLUpdatePrivacy() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public TLAbsPrivacyKey getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLAbsPrivacyKey key) {
        this.key = key;
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public TLVector<TLAbsPrivacyRule> getRules() {
        return this.rules;
    }

    /**
     * Sets rules.
     *
     * @param rules the rules
     */
    public void setRules(TLVector<TLAbsPrivacyRule> rules) {
        this.rules = rules;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.key, stream);
        StreamingUtils.writeTLVector(this.rules, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.key = (TLAbsPrivacyKey) StreamingUtils.readTLObject(stream, context);
        this.rules = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "updatePrivacy#ee3b272a";
    }
}