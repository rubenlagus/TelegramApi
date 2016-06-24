/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Representation of an account with password
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLAccountPassword extends TLAbsAccountPassword {
    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x7c18141c;

    private TLBytes currentSalt; ///< Current salt that need to be transmited with the password when logging
    private String hint = ""; ///< Hint for the password
    private boolean hasRecovery; ///< true if the password recovery is enable for this account

    /**
     * Instantiates a new TL account password.
     */
    public TLAccountPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets current salt.
     *
     * @return the current salt
     */
    public TLBytes getCurrentSalt() {
        return this.currentSalt;
    }

    /**
     * Sets current salt.
     *
     * @param currentSalt the current salt
     */
    public void setCurrentSalt(TLBytes currentSalt) {
        this.currentSalt = currentSalt;
    }

    /**
     * Gets hint.
     *
     * @return the hint
     */
    public String getHint() {
        return this.hint;
    }

    /**
     * Sets hint.
     *
     * @param hint the hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * If the account has recovery enabled.
     *
     * @return the boolean
     */
    public boolean isHasRecovery() {
        return this.hasRecovery;
    }

    /**
     * Sets if the account has revocery enabled.
     *
     * @param hasRecovery the has recovery
     */
    public void setHasRecovery(boolean hasRecovery) {
        this.hasRecovery = hasRecovery;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.currentSalt, stream);
        StreamingUtils.writeTLBytes(this.newSalt, stream);
        StreamingUtils.writeTLString(this.hint, stream);
        StreamingUtils.writeTLBool(this.hasRecovery, stream);
        StreamingUtils.writeTLString(this.emailUnconfirmedPattern, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.currentSalt = StreamingUtils.readTLBytes(stream, context);
        this.newSalt = StreamingUtils.readTLBytes(stream, context);
        this.hint = StreamingUtils.readTLString(stream);
        this.hasRecovery = StreamingUtils.readTLBool(stream);
        this.emailUnconfirmedPattern = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "accountPassword#7c18141c";
    }
}
