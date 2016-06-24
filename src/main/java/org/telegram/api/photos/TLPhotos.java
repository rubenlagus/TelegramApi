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
 * The type TL photos.
 */
public class TLPhotos extends TLAbsPhotos {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8dca6aa5;

    /**
     * Instantiates a new TL photos.
     */
    public TLPhotos() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.photos, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.photos = (TLVector<TLAbsPhoto>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "photos.photos#8dca6aa5";
    }
}