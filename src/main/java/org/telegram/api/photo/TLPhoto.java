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
    public static final int CLASS_ID = 0x9288dd29 ;

    private static final int FLAG_STICKERS = 0x00000001; // 0

    private int flags;
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

    public boolean hasStickers() {
        return (flags & FLAG_STICKERS) != 0;
    }
    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.sizes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.sizes = StreamingUtils.readTLVector(stream, context, TLAbsPhotoSize.class);
    }

    @Override
    public String toString() {
        return "photo#9288dd29";
    }
}
