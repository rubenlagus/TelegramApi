/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.encrypted.chat;

import org.telegram.tl.TLObject;

/**
 * Abstract class that represent an encrypted chat with another user
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public abstract class TLAbsEncryptedChat extends TLObject {

    protected int id; ///< Id of the conversation

    /**
     * Instantiates a new TL abs encrypted chat.
     */
    protected TLAbsEncryptedChat() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(int value) {
        this.id = value;
    }
}