/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 21/11/14.
 */
package org.telegram.api.account;

import org.telegram.api.privacy.privacyrule.TLAbsPrivacyRule;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Privacy configuration of the account
 * @author Ruben Bermudez
 * @version 2.0
 * @date 21 /11/14
 */
public class TLAccountPrivacyRules extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x554abb6f;

    private TLVector<TLAbsPrivacyRule> rules; ///< List of privacy rules
    private TLVector<TLAbsUser> users; ///< Users present in the rules

    public TLAccountPrivacyRules() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.rules, stream);
        StreamingUtils.writeTLVector(this.users, stream);

    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.rules = (TLVector<TLAbsPrivacyRule>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "account.PrivacyRules#554abb6f";
    }
}
