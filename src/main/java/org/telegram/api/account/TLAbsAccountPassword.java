/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.account;

import org.telegram.tl.TLBytes;
import org.telegram.tl.TLObject;

/**
 * Abstract type to represent the password of an account.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9/01/15
 */
public abstract class TLAbsAccountPassword extends TLObject {
    /**
     * Salt for this authentication
     */
    protected TLBytes newSalt;
    /**
     * The Email unconfirmed pattern.
     */
    protected String emailUnconfirmedPattern;

    /**
     * Instantiates a new TL abs account password.
     */
    protected TLAbsAccountPassword() {
        super();
    }

    /**
     * Gets new salt.
     *
     * @return the new salt
     */
    public TLBytes getNewSalt() {
        return this.newSalt;
    }

    /**
     * Sets new salt.
     *
     * @param newSalt the new salt
     */
    public void setNewSalt(TLBytes newSalt) {
        this.newSalt = newSalt;
    }

    /**
     * Gets email unconfirmed pattern.
     *
     * @return the email unconfirmed pattern
     */
    public String getEmailUnconfirmedPattern() {
        return this.emailUnconfirmedPattern;
    }

    /**
     * Sets email unconfirmed pattern.
     *
     * @param emailUnconfirmedPattern the email unconfirmed pattern
     */
    public void setEmailUnconfirmedPattern(String emailUnconfirmedPattern) {
        this.emailUnconfirmedPattern = emailUnconfirmedPattern;
    }
}
