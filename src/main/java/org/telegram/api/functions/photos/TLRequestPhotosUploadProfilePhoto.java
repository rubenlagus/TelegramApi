package org.telegram.api.functions.photos;

import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.api.input.geopoint.TLAbsInputGeoPoint;
import org.telegram.api.input.photo.crop.TLAbsInputPhotoCrop;
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
    public static final int CLASS_ID = 0xd50f9c88;

    private TLAbsInputFile file;
    private String caption;
    private TLAbsInputGeoPoint geoPoint;
    private TLAbsInputPhotoCrop crop;

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
        throw new IOException("Incorrect response type. Expected org.telegram.api.photo.TLPhoto, got: " + res.getClass().getCanonicalName());
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

    /**
     * Gets caption.
     *
     * @return the caption
     */
    public String getCaption() {
        return this.caption;
    }

    /**
     * Sets caption.
     *
     * @param value the value
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    /**
     * Gets geo point.
     *
     * @return the geo point
     */
    public TLAbsInputGeoPoint getGeoPoint() {
        return this.geoPoint;
    }

    /**
     * Sets geo point.
     *
     * @param value the value
     */
    public void setGeoPoint(TLAbsInputGeoPoint value) {
        this.geoPoint = value;
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
        StreamingUtils.writeTLObject(this.file, stream);
        StreamingUtils.writeTLString(this.caption, stream);
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeTLObject(this.crop, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.file = ((TLAbsInputFile) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
        this.geoPoint = ((TLAbsInputGeoPoint) StreamingUtils.readTLObject(stream, context));
        this.crop = ((TLAbsInputPhotoCrop) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "photos.uploadProfilePhoto#d50f9c88";
    }
}