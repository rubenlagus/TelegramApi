/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 21/11/14.
 */
package org.telegram.api.account;

import org.telegram.api.auth.TLAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Authorization of this account in other devices.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 21 /11/14
 */
public class TLAccountAuthorizations extends TLObject {
    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0x1250abde;

    private TLVector<TLAuthorization> authorizations; ///< List of authorizations

    /**
     * Instantiates a new TL account authorizations.
     */
    public TLAccountAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets authorizations.
     *
     * @return the authorizations
     */
    public TLVector<TLAuthorization> getAuthorizations() {
        return this.authorizations;
    }

    /**
     * Sets authorizations.
     *
     * @param authorizations the authorizations
     */
    public void setAuthorizations(TLVector<TLAuthorization> authorizations) {
        this.authorizations = authorizations;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.authorizations, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.authorizations = (TLVector<TLAuthorization>) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "account.authorizations#1250abde";
    }
}
