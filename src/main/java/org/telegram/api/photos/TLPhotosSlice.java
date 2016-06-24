package org.telegram.api.photos;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photos slice.
 */
public class TLPhotosSlice extends TLAbsPhotos {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15051f54;

    /**
     * The Count.
     */
    protected int count;

    /**
     * Instantiates a new TL photos slice.
     */
    public TLPhotosSlice() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.photos, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.photos = (TLVector<TLAbsPhoto>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "photos.photosSlice#15051f54";
    }
}