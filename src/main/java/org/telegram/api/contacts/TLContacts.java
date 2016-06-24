/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts;

import org.telegram.api.contact.TLContact;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Current user contacts list and information on the users
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContacts extends TLAbsContacts {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6f8b8cb2;

    private TLVector<TLContact> contacts; ///< List of contacts
    private TLVector<TLAbsUser> users; ///< User references in the list of contacts

    /**
     * Instantiates a new TL contacts.
     */
    public TLContacts() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets contacts.
     *
     * @return the contacts
     */
    public TLVector<TLContact> getContacts() {
        return this.contacts;
    }

    /**
     * Sets contacts.
     *
     * @param contacts the contacts
     */
    public void setContacts(TLVector<TLContact> contacts) {
        this.contacts = contacts;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.contacts, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.contacts = (TLVector<TLContact>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.contacts#6f8b8cb2";
    }
}