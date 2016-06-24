/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts.blocked;

import org.telegram.api.contact.TLContactBlocked;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Full list of blocked users
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLBlocked extends TLAbsBlocked {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c138d15;

    /**
     * Instantiates a new TL blocked.
     */
    public TLBlocked() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.blocked, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.blocked = (TLVector<TLContactBlocked>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.blocked#1c138d15";
    }
}