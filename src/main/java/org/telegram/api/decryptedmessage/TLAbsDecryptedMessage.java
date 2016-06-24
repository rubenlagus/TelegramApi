/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.tl.TLObject;

/**
 * Abstract class to describes the content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public abstract class TLAbsDecryptedMessage extends TLObject {

    /**
     * Random message ID, assigned by the author of message. Must be equal to the ID passed to sending method.
     */
    protected long randomId;

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public long getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param value the value
     */
    public void setRandomId(long value) {
        this.randomId = value;
    }
}