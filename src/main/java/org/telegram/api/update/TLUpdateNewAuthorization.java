package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new authorization.
 */
public class TLUpdateNewAuthorization extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8f06529a;

    private long authKeyId;
    private String location;
    private String device;
    private int date;

    /**
     * Instantiates a new TL update new authorization.
     */
    public TLUpdateNewAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets auth key id.
     *
     * @return the auth key id
     */
    public long getAuthKeyId() {
        return this.authKeyId;
    }

    /**
     * Sets auth key id.
     *
     * @param authKeyId the auth key id
     */
    public void setAuthKeyId(long authKeyId) {
        this.authKeyId = authKeyId;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public String getDevice() {
        return this.device;
    }

    /**
     * Sets device.
     *
     * @param device the device
     */
    public void setDevice(String device) {
        this.device = device;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.authKeyId, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.device, stream);
        StreamingUtils.writeTLString(this.location, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.authKeyId = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.device = StreamingUtils.readTLString(stream);
        this.location = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "updateNewAuthorization#8f06529a";
    }
}