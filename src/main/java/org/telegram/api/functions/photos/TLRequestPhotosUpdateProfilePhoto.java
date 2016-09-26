package org.telegram.api.functions.photos;

import org.telegram.api.input.photo.TLAbsInputPhoto;
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
    public static final int CLASS_ID = 0xf0bb5152;

    private TLAbsInputPhoto id;

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
        throw new IOException("Incorrect response type. Expected " + TLAbsUserProfilePhoto.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputPhoto.class);
    }

    public String toString() {
        return "photos.updateProfilePhoto#f0bb5152";
    }
}