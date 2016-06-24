package org.telegram.api.wallpaper;

import org.telegram.api.photo.size.TLAbsPhotoSize;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL wall paper.
 */
public class TLWallPaper extends TLAbsWallPaper {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xccb03657;

    private TLVector<TLAbsPhotoSize> sizes;

    /**
     * Instantiates a new TL wall paper.
     */
    public TLWallPaper() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLVector(this.sizes, stream);
        StreamingUtils.writeInt(this.color, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.sizes = (TLVector<TLAbsPhotoSize>) StreamingUtils.readTLVector(stream, context);
        this.color = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "wallPaper#ccb03657";
    }
}