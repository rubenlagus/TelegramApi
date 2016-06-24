package org.telegram.api.updates.difference;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.encrypted.message.TLAbsEncryptedMessage;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * The type TL abs difference.
 */
public abstract class TLAbsDifference extends TLObject {
    /**
     * The Date.
     */
    protected int date;
    /**
     * The Seq.
     */
    protected int seq;
    /**
     * The New messages.
     */
    protected TLVector<TLAbsMessage> newMessages = new TLVector<>();
    /**
     * The New encrypted messages.
     */
    protected TLVector<TLAbsEncryptedMessage> newEncryptedMessages = new TLVector<>();
    /**
     * The Other updates.
     */
    protected TLVector<TLAbsUpdate> otherUpdates = new TLVector<>();
    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats = new TLVector<>();
    /**
     * The Users.
     */
    protected TLVector<TLAbsUser> users = new TLVector<>();

    /**
     * Instantiates a new TL abs difference.
     */
    protected TLAbsDifference() {
        super();
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets seq.
     *
     * @return the seq
     */
    public int getSeq() {
        return this.seq;
    }

    /**
     * Sets seq.
     *
     * @param seq the seq
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

    /**
     * Gets new messages.
     *
     * @return the new messages
     */
    public TLVector<TLAbsMessage> getNewMessages() {
        return this.newMessages;
    }

    /**
     * Sets new messages.
     *
     * @param newMessages the new messages
     */
    public void setNewMessages(TLVector<TLAbsMessage> newMessages) {
        this.newMessages = newMessages;
    }

    /**
     * Gets new encrypted messages.
     *
     * @return the new encrypted messages
     */
    public TLVector<TLAbsEncryptedMessage> getNewEncryptedMessages() {
        return this.newEncryptedMessages;
    }

    /**
     * Sets new encrypted messages.
     *
     * @param newEncryptedMessages the new encrypted messages
     */
    public void setNewEncryptedMessages(TLVector<TLAbsEncryptedMessage> newEncryptedMessages) {
        this.newEncryptedMessages = newEncryptedMessages;
    }

    /**
     * Gets other updates.
     *
     * @return the other updates
     */
    public TLVector<TLAbsUpdate> getOtherUpdates() {
        return this.otherUpdates;
    }

    /**
     * Sets other updates.
     *
     * @param otherUpdates the other updates
     */
    public void setOtherUpdates(TLVector<TLAbsUpdate> otherUpdates) {
        this.otherUpdates = otherUpdates;
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