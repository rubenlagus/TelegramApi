package org.telegram.api.photo.size;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photo size empty.
 */
public class TLPhotoSizeEmpty extends TLAbsPhotoSize {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe17e23c;
    private String type;

    /**
     * Instantiates a new TL photo size empty.
     */
    public TLPhotoSizeEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.type = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "photoSizeEmpty#e17e23c";
    }
}