/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.encrypted.chat;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Empty encrypted chat with another user
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLEncryptedChatEmpty extends TLAbsEncryptedChat {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xab7ec0a0;

    /**
     * Instantiates a new TL encrypted chat empty.
     */
    public TLEncryptedChatEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "encryptedChatEmpty#ab7ec0a0";
    }
}