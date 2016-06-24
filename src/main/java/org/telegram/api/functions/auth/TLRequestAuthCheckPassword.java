package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth check password.
 */
public class TLRequestAuthCheckPassword extends TLMethod<TLAuthorization> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa63011e;

    private TLBytes passwordHash;

    /**
     * Instantiates a new TL request auth check password.
     */
    public TLRequestAuthCheckPassword() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAuthorization deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAuthorization))
            return (TLAuthorization) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets password hash.
     *
     * @return the password hash
     */
    public TLBytes getPasswordHash() {
        return this.passwordHash;
    }

    /**
     * Sets password hash.
     *
     * @param passwordHash the password hash
     */
    public void setPasswordHash(TLBytes passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.passwordHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.passwordHash = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "auth.checkPassword#a63011e";
    }
}