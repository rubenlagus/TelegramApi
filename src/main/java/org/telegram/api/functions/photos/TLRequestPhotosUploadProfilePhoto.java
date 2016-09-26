package org.telegram.api.functions.photos;

import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.api.photo.TLPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos upload profile photo.
 */
public class TLRequestPhotosUploadProfilePhoto extends TLMethod<TLPhoto> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f32c098;

    private TLAbsInputFile file;


    /**
     * Instantiates a new TL request photos upload profile photo.
     */
    public TLRequestPhotosUploadProfilePhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLPhoto deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLPhoto))
            return (TLPhoto) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsPhoto.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsInputFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param value the value
     */
    public void setFile(TLAbsInputFile value) {
        this.file = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
    }

    public String toString() {
        return "photos.uploadProfilePhoto#4f32c098";
    }
}