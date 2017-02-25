/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLAccountTmpPassword extends TLObject {
    public static final int CLASS_ID = 0xdb64fd34;

    private TLBytes tmpPassword;
    private int validUntil;

    public TLAccountTmpPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getTmpPassword() {
        return tmpPassword;
    }

    public int getValidUntil() {
        return validUntil;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.tmpPassword, stream);
        StreamingUtils.writeInt(this.validUntil, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.tmpPassword = StreamingUtils.readTLBytes(stream, context);
        this.validUntil = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "account.tmpPassword#db64fd34";
    }
}
