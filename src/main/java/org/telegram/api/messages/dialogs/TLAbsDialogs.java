package org.telegram.api.messages.dialogs;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.dialog.TLDialog;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * The type TL abs dialogs.
 */
public abstract class TLAbsDialogs extends TLObject {
    /**
     * The Dialogs.
     */
    protected TLVector<TLDialog> dialogs;
    /**
     * The Messages.
     */
    protected TLVector<TLAbsMessage> messages;
    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats;
    /**
     * The Users.
     */
    protected TLVector<TLAbsUser> users;

    /**
     * Instantiates a new TL abs dialogs.
     */
    protected TLAbsDialogs() {
        super();
    }

    /**
     * Gets dialogs.
     *
     * @return the dialogs
     */
    public TLVector<TLDialog> getDialogs() {
        return this.dialogs;
    }

    /**
     * Sets dialogs.
     *
     * @param value the value
     */
    public void setDialogs(TLVector<TLDialog> value) {
        this.dialogs = value;
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public TLVector<TLAbsMessage> getMessages() {
        return this.messages;
    }

    /**
     * Sets messages.
     *
     * @param value the value
     */
    public void setMessages(TLVector<TLAbsMessage> value) {
        this.messages = value;
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
     * @param value the value
     */
    public void setChats(TLVector<TLAbsChat> value) {
        this.chats = value;
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