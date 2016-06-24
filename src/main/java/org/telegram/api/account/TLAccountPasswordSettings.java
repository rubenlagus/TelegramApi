/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Account password settings.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAccountPasswordSettings
 * @date 9 /01/15
 */
public class TLAccountPasswordSettings extends TLObject {
    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0xb7b72ab3;

    private String email; ///< Recovery email for this account

    /**
     * Instantiates a new TL account password settings.
     */
    public TLAccountPasswordSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.email, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.email = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "accountPasswordSettings#b7b72ab3";
    }
}
