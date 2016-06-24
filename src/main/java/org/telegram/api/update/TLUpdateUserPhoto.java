package org.telegram.api.update;

import org.telegram.api.user.profile.photo.TLAbsUserProfilePhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update user photo.
 */
public class TLUpdateUserPhoto extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95313b0c;

    private int userId;
    private boolean previous;
    private TLAbsUserProfilePhoto photo;
    private int date;

    /**
     * Instantiates a new TL update user photo.
     */
    public TLUpdateUserPhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Is previous.
     *
     * @return the boolean
     */
    public boolean isPrevious() {
        return this.previous;
    }

    /**
     * Sets previous.
     *
     * @param previous the previous
     */
    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public TLAbsUserProfilePhoto getPhoto() {
        return this.photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(TLAbsUserProfilePhoto photo) {
        this.photo = photo;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeTLBool(this.previous, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.photo = ((TLAbsUserProfilePhoto) StreamingUtils.readTLObject(stream, context));
        this.previous = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "updateUserPhoto#95313b0c";
    }
}