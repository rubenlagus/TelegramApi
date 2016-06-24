/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contact.link;

import org.telegram.tl.TLObject;

/**
 * Abstract class to represent a connection between another user and current one
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public abstract class TLAbsContactLink extends TLObject {

    protected boolean contact; ///< True if the other user is a contact of current one

    /**
     * Instantiates a new TL abs contact link.
     */
    protected TLAbsContactLink() {
        super();
    }

    /**
     * Is contact.
     *
     * @return the boolean
     */
    public boolean isContact() {
        return this.contact;
    }

    /**
     * Sets contact.
     *
     * @param contact the contact
     */
    public void setContact(boolean contact) {
        this.contact = contact;
    }
}