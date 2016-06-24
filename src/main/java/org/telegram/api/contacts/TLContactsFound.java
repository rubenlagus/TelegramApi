/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.peer.TLAbsPeer;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Information of users found by name substring and auxiliary data.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactsFound extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1aa1f784;

    private TLVector<TLAbsPeer> results; ///< List of contacts found
    private TLVector<TLAbsChat> chats; ///< List of contacts found
    private TLVector<TLAbsUser> users; ///< List of user referenced in contacts found

    /**
     * Instantiates a new TL contacts found.
     */
    public TLContactsFound() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPeer> getResults() {
        return results;
    }

    public void setResults(TLVector<TLAbsPeer> results) {
        this.results = results;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.results, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.results = (TLVector<TLAbsPeer>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.found#1aa1f784";
    }
}