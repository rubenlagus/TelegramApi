/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts;

import org.telegram.api.contact.TLImportedContact;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Info on succesfully imported contacts
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLImportedContacts extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xad524315;

    private TLVector<TLImportedContact> imported; ///< List of imported contacts
    private TLLongVector retryContacts = new TLLongVector(); ///< List of contact ids that could not be imported due to system limitation and will need to be imported at a later date
    private TLVector<TLAbsUser> users; ///< List of users referenced in imported contacts

    /**
     * Instantiates a new TL imported contacts.
     */
    public TLImportedContacts() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets imported.
     *
     * @return the imported
     */
    public TLVector<TLImportedContact> getImported() {
        return this.imported;
    }

    /**
     * Sets imported.
     *
     * @param value the value
     */
    public void setImported(TLVector<TLImportedContact> value) {
        this.imported = value;
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

    /**
     * Gets retry contacts.
     *
     * @return the retry contacts
     */
    public TLLongVector getRetryContacts() {
        return this.retryContacts;
    }

    /**
     * Sets retry contacts.
     *
     * @param retryContacts the retry contacts
     */
    public void setRetryContacts(TLLongVector retryContacts) {
        this.retryContacts = retryContacts;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.imported, stream);
        StreamingUtils.writeTLVector(this.retryContacts, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.imported = (TLVector<TLImportedContact>) StreamingUtils.readTLVector(stream, context);
        this.retryContacts = StreamingUtils.readTLLongVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.importedContacts#ad524315";
    }
}