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
 * Info on successfully resolved peers
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLResolvedPeer extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f077ad9;

    private TLAbsPeer peer;
    private TLVector<TLAbsChat> chats;
    private TLVector<TLAbsUser> users;

    public TLResolvedPeer() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
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
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.resolvedPeer#7f077ad9";
    }
}