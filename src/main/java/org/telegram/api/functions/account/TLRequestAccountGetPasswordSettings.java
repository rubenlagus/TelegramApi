package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountPasswordSettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account get password settings.
 */
public class TLRequestAccountGetPasswordSettings extends TLMethod<TLAccountPasswordSettings> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc8d11bb;

    private TLBytes currentPasswordHash;

    /**
     * Instantiates a new TL request account get password settings.
     */
    public TLRequestAccountGetPasswordSettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAccountPasswordSettings deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAccountPasswordSettings))
            return (TLAccountPasswordSettings) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAccountPasswordSettings, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets current password hash.
     *
     * @return the current password hash
     */
    public TLBytes getCurrentPasswordHash() {
        return this.currentPasswordHash;
    }

    /**
     * Sets current password hash.
     *
     * @param currentPasswordHash the current password hash
     */
    public void setCurrentPasswordHash(TLBytes currentPasswordHash) {
        this.currentPasswordHash = currentPasswordHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.currentPasswordHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.currentPasswordHash = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "account.getPasswordSettings#bc8d11bb";
    }
}