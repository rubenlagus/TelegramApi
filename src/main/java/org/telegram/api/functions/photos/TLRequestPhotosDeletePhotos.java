package org.telegram.api.functions.photos;

import org.telegram.api.input.photo.TLAbsInputPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos delete photos.
 */
public class TLRequestPhotosDeletePhotos extends TLMethod<TLLongVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x87cf7f2f;

    private TLVector<TLAbsInputPhoto> id;

    /**
     * Instantiates a new TL request photos delete photos.
     */
    public TLRequestPhotosDeletePhotos() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLLongVector))
            return (TLLongVector) res;
        throw new IOException("Incorrect response type. Expected org.telegram.TLLongVector, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLVector<TLAbsInputPhoto> getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(TLVector<TLAbsInputPhoto> id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "photos.deletePhotos#87cf7f2f";
    }
}