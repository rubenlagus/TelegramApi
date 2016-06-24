/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contents of an encrypted service message.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessageService extends TLAbsDecryptedMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x73164160;

    private TLDecryptedMessageAction action; ///< Action relevant to the service message

    /**
     * Instantiates a new TL decrypted message service.
     */
    public TLDecryptedMessageService() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public TLDecryptedMessageAction getAction() {
        return this.action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(TLDecryptedMessageAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.randomId = StreamingUtils.readLong(stream);
        this.action = (TLDecryptedMessageAction) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "decryptedMessageService#73164160";
    }
}