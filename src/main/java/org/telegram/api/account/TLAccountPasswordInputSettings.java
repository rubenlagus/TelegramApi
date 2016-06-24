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

/**
 * Settings for an account password.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLAccountPasswordInputSettings extends TLObject {
    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x86916deb;

    private static final int FLAG_SALT    = 0x00000001; // 0
    private static final int FLAG_EMAIL   = 0x00000002; // 1

    /**
     * @brief Binary flags
     * Flag  | Description
     * ----- | -----------------------------------------------
     * 0x1   | ´newSalt, ´newPassword´and ´hint´ are present
     * 0x2   | ´email´ is present
     */
    private int flags;
    private TLBytes newSalt; ///< Salt for this password
    private TLBytes newPasswordHash; ///< New password hash value
    private String hint; ///< Hint for new password
    private String email; ///< Email for recovery

    /**
     * Instantiates a new TL account password input settings.
     */
    public TLAccountPasswordInputSettings() {
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

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets new salt.
     *
     * @return the new salt
     */
    public TLBytes getNewSalt() {
        return this.newSalt;
    }

    /**
     * Sets new salt.
     *
     * @param newSalt the new salt
     */
    public void setNewSalt(TLBytes newSalt) {
        this.newSalt = newSalt;
    }

    /**
     * Gets new password hash.
     *
     * @return the new password hash
     */
    public TLBytes getNewPasswordHash() {
        return this.newPasswordHash;
    }

    /**
     * Sets new password hash.
     *
     * @param newPasswordHash the new password hash
     */
    public void setNewPasswordHash(TLBytes newPasswordHash) {
        this.newPasswordHash = newPasswordHash;
    }

    /**
     * Gets hint.
     *
     * @return the hint
     */
    public String getHint() {
        return this.hint;
    }

    /**
     * Sets hint.
     *
     * @param hint the hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_SALT) != 0) {
            StreamingUtils.writeTLBytes(this.newSalt, stream);
            StreamingUtils.writeTLBytes(this.newPasswordHash, stream);
            StreamingUtils.writeTLString(this.hint, stream);
        }
        if ((this.flags & FLAG_EMAIL) != 0) {
            StreamingUtils.writeTLString(this.email, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_SALT) != 0) {
            this.newSalt = StreamingUtils.readTLBytes(stream, context);
            this.newPasswordHash = StreamingUtils.readTLBytes(stream, context);
            this.hint = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_EMAIL) != 0) {
            this.email = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "accountPasswordInputSettings#86916deb";
    }
}
