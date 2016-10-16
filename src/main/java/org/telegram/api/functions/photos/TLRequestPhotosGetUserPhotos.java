package org.telegram.api.functions.photos;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.photos.TLAbsPhotos;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos get user photos.
 */
public class TLRequestPhotosGetUserPhotos extends TLMethod<TLAbsPhotos> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x91cd32a8;

    private TLAbsInputUser userId;
    private int offset;
    private long maxId;
    private int limit;

    /**
     * Instantiates a new TL request photos get user photos.
     */
    public TLRequestPhotosGetUserPhotos() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhotos deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsPhotos)) {
            return (TLAbsPhotos) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsPhotos.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public long getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param value the value
     */
    public void setMaxId(long value) {
        this.maxId = value;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Sets limit.
     *
     * @param value the value
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeLong(this.maxId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
        this.offset = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readLong(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "photos.getUserPhotos#91cd32a8";
    }
}