/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.auth;

import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Authorization of a user
 * @author Ruben Bermudez
 * @version 2.0
 * @date 13 /11/14
 */
public class TLAuthorization extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xff036af1;

    private TLAbsUser user; ///< Authorized user

    /**
     * Instantiates a new TL authorization.
     */
    public TLAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public TLAbsUser getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param value the value
     */
    public void setUser(TLAbsUser value) {
        this.user = value;
    }


    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.user, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.user = ((TLAbsUser) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "auth.authorization#ff036af1";
    }
}