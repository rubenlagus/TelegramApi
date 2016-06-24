package org.telegram.api.functions.account;

import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account update profile.
 */
public class TLRequestAccountUpdateProfile extends TLMethod<TLAbsUser> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x78515775;

    private static final int FLAG_FIRSTNAME  = 0x00000001; // 0
    private static final int FLAG_LASTNAME   = 0x00000002; // 1
    private static final int FLAG_ABOUT      = 0x00000004; // 2
/*
account.updateProfile#78515775
flags:#
first_name:flags.0?string
last_name:flags.1?string about:flags.2?string = User;

 */
    private int flags;
    private String firstName;
    private String lastName;
    private String about;

    /**
     * Instantiates a new TL request account update profile.
     */
    public TLRequestAccountUpdateProfile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUser deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUser)) {
            return (TLAbsUser) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUser.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param value the value
     */
    public void setFirstName(String value) {
        this.firstName = value;
        enableFlag(FLAG_FIRSTNAME, (value != null) && !value.isEmpty());
    }

    private void enableFlag(int flag, boolean enabled) {
        if (enabled) {
            this.flags |= flag;
        } else {
            this.flags &= ~flag;
        }
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param value the value
     */
    public void setLastName(String value) {
        this.lastName = value;
        enableFlag(FLAG_LASTNAME, (value != null) && !value.isEmpty());
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String value) {
        this.about = value;
        enableFlag(FLAG_ABOUT, value != null);
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_FIRSTNAME) != 0) {
            StreamingUtils.writeTLString(this.firstName, stream);
        }
        if ((flags & FLAG_LASTNAME) != 0) {
            StreamingUtils.writeTLString(this.lastName, stream);
        }
        if ((flags & FLAG_ABOUT) != 0) {
            StreamingUtils.writeTLString(this.about, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_FIRSTNAME) != 0) {
            this.firstName = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_LASTNAME) != 0) {
            this.lastName = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_ABOUT) != 0) {
            this.about = StreamingUtils.readTLString(stream);
        }
    }

    public String toString() {
        return "account.updateProfile#78515775";
    }
}