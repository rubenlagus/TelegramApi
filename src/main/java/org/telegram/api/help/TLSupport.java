package org.telegram.api.help;

import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL support.
 */
public class TLSupport extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x17c6b5f6;

    private String phoneNumber;
    private TLAbsUser user;

    /**
     * Instantiates a new TL support.
     */
    public TLSupport() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param value the value
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLObject(this.user, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.user = ((TLAbsUser) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "help.support#17c6b5f6";
    }
}