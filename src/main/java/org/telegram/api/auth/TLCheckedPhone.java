/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.auth;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Phone checking confirmation.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 12 /11/14
 */
public class TLCheckedPhone extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x811ea28e;

    private boolean phoneRegistered; ///< True if the phone is registered in Telegram, false otherwise

    /**
     * Instantiates a new TL checked phone.
     */
    public TLCheckedPhone() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Is phone registered.
     *
     * @return the boolean
     */
    public boolean isPhoneRegistered() {
        return this.phoneRegistered;
    }

    /**
     * Sets phone registered.
     *
     * @param phoneRegistered the phone registered
     */
    public void setPhoneRegistered(boolean phoneRegistered) {
        this.phoneRegistered = phoneRegistered;
    }

    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBool(this.phoneRegistered, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneRegistered = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "auth.checkedPhone#811ea28e";
    }
}