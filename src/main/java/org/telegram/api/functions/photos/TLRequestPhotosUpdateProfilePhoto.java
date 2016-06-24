package org.telegram.api.functions.photos;

import org.telegram.api.input.photo.TLAbsInputPhoto;
import org.telegram.api.input.photo.crop.TLAbsInputPhotoCrop;
import org.telegram.api.user.profile.photo.TLAbsUserProfilePhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos update profile photo.
 */
public class TLRequestPhotosUpdateProfilePhoto extends TLMethod<TLAbsUserProfilePhoto> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeef579a0;

    private TLAbsInputPhoto id;
    private TLAbsInputPhotoCrop crop;

    /**
     * Instantiates a new TL request photos update profile photo.
     */
    public TLRequestPhotosUpdateProfilePhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUserProfilePhoto deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUserProfilePhoto))
            return (TLAbsUserProfilePhoto) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.user.profile.photo.TLAbsUserProfilePhoto, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLAbsInputPhoto getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLAbsInputPhoto value) {
        this.id = value;
    }

    /**
     * Gets crop.
     *
     * @return the crop
     */
    public TLAbsInputPhotoCrop getCrop() {
        return this.crop;
    }

    /**
     * Sets crop.
     *
     * @param value the value
     */
    public void setCrop(TLAbsInputPhotoCrop value) {
        this.crop = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLObject(this.crop, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = ((TLAbsInputPhoto) StreamingUtils.readTLObject(stream, context));
        this.crop = ((TLAbsInputPhotoCrop) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "photos.updateProfilePhoto#eef579a0";
    }
}