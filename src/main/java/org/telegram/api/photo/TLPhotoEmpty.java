package org.telegram.api.photo;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photo empty.
 */
public class TLPhotoEmpty extends TLAbsPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2331b22d;

    private long id;

    /**
     * Instantiates a new TL photo empty.
     */
    public TLPhotoEmpty() {
        super();
    }

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "photoEmpty#2331b22d";
    }
}