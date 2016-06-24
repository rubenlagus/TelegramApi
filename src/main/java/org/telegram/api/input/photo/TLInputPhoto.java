package org.telegram.api.input.photo;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input photo.
 */
public class TLInputPhoto extends TLAbsInputPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfb95c6c4;

    private long id;
    private long accessHash;

    /**
     * Instantiates a new TL input photo.
     */
    public TLInputPhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "inputPhoto#fb95c6c4";
    }
}