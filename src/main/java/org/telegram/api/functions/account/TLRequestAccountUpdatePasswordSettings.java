package org.telegram.api.functions.account;

import org.telegram.api.account.TLAccountPasswordInputSettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account update password settings.
 */
public class TLRequestAccountUpdatePasswordSettings extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa7c4b86;

    private TLBytes currentPasswordHash;
    private TLAccountPasswordInputSettings newSettings;

    /**
     * Instantiates a new TL request account update password settings.
     */
    public TLRequestAccountUpdatePasswordSettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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

    /**
     * Gets new settings.
     *
     * @return the new settings
     */
    public TLAccountPasswordInputSettings getNewSettings() {
        return this.newSettings;
    }

    /**
     * Sets new settings.
     *
     * @param newSettings the new settings
     */
    public void setNewSettings(TLAccountPasswordInputSettings newSettings) {
        this.newSettings = newSettings;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.currentPasswordHash, stream);
        StreamingUtils.writeTLObject(this.newSettings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.currentPasswordHash = StreamingUtils.readTLBytes(stream, context);
        this.newSettings = (TLAccountPasswordInputSettings) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "account.updatePasswordSettings#fa7c4b86";
    }
}