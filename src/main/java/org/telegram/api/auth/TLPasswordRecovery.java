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
 * Password recovery information
 * @author Ruben Bermudez
 * @version 2.0
 * @date 12 /11/14
 */
public class TLPasswordRecovery extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x137948a5;

    private String emailPattern; ///< Email where the recovery information was sent

    /**
     * Instantiates a new TL password recovery.
     */
    public TLPasswordRecovery() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets email pattern.
     *
     * @return the email pattern
     */
    public String getEmailPattern() {
        return this.emailPattern;
    }

    /**
     * Sets email pattern.
     *
     * @param emailPattern the email pattern
     */
    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.emailPattern, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.emailPattern = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "auth.passwordrecovery#137948a5";
    }
}