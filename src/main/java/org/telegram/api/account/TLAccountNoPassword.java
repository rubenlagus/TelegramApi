/**
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Representation of an account without password
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLAccountNoPassword extends TLAbsAccountPassword {
    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0x96dabc18;

    /**
     * Instantiates a new TL account no password.
     */
    public TLAccountNoPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.newSalt, stream);
        StreamingUtils.writeTLString(this.emailUnconfirmedPattern, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.newSalt = StreamingUtils.readTLBytes(stream, context);
        this.emailUnconfirmedPattern = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "accountNoPassword#96dabc18";
    }
}
