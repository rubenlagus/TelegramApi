/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts.blocked;

import org.telegram.api.contact.TLContactBlocked;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * Abstract class to represent blocked users
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public abstract class TLAbsBlocked extends TLObject {

    protected TLVector<TLContactBlocked> blocked; ///< List of blocked users
    protected TLVector<TLAbsUser> users; ///< List of user referenced in blocked users

    /**
     * Instantiates a new TL abs blocked.
     */
    protected TLAbsBlocked() {
        super();
    }

    /**
     * Gets blocked.
     *
     * @return the blocked
     */
    public TLVector<TLContactBlocked> getBlocked() {
        return this.blocked;
    }

    /**
     * Sets blocked.
     *
     * @param value the value
     */
    public void setBlocked(TLVector<TLContactBlocked> value) {
        this.blocked = value;
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
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }
}