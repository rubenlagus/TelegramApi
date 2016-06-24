package org.telegram.api.photo;

import org.telegram.api.photo.size.TLAbsPhotoSize;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photo.
 */
public class TLPhoto extends TLAbsPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcded42fe;

    private long id;
    private long accessHash;
    private int date;
    private TLVector<TLAbsPhotoSize> sizes;

    /**
     * Instantiates a new TL photo.
     */
    public TLPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
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
     * Gets sizes.
     *
     * @return the sizes
     */
    public TLVector<TLAbsPhotoSize> getSizes() {
        return this.sizes;
    }

    /**
     * Sets sizes.
     *
     * @param sizes the sizes
     */
    public void setSizes(TLVector<TLAbsPhotoSize> sizes) {
        this.sizes = sizes;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.sizes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.sizes = (TLVector<TLAbsPhotoSize>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "photos.photo#cded42fe";
    }
}
