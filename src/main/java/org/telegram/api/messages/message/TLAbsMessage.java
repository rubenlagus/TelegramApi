package org.telegram.api.messages.message;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * The type TL abs message.
 */
public abstract class TLAbsMessage extends TLObject {
    /**
     * The Message.
     */
    public org.telegram.api.message.TLAbsMessage message;
    /**
     * The Chats.
     */
    public TLVector<TLAbsChat> chats = new TLVector<>();
    /**
     * The Users.
     */
    public TLVector<TLAbsUser> users = new TLVector<>();

    /**
     * Instantiates a new TL abs message.
     */
    protected TLAbsMessage() {
        super();
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public org.telegram.api.message.TLAbsMessage getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(org.telegram.api.message.TLAbsMessage message) {
        this.message = message;
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param chats the chats
     */
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
     * @param users the users
     */
    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }
}