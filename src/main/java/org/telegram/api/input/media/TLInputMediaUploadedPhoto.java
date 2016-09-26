package org.telegram.api.input.media;

import org.telegram.api.input.document.TLAbsInputDocument;
import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media uploaded photo.
 */
public class TLInputMediaUploadedPhoto extends TLAbsInputMedia {
    public static final int CLASS_ID = 0x630c9af1;

    private static final int FLAG_STICKERS       = 0x00000001; // 0

    private int flags;
    private TLAbsInputFile file;
    private String caption;
    private TLVector<TLAbsInputDocument> stickers;

    /**
     * Instantiates a new TL input media uploaded photo.
     */
    public TLInputMediaUploadedPhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * @param caption the caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public TLVector<TLAbsInputDocument> getStickers() {
        return stickers;
    }

    public void setStickers(TLVector<TLAbsInputDocument> stickers) {
        if (stickers == null || stickers.isEmpty()) {
            flags &= ~FLAG_STICKERS;
        } else {
            flags |= FLAG_STICKERS;
        }
        this.stickers = stickers;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(this.file, stream);
        StreamingUtils.writeTLString(this.caption, stream);
        if ((flags & FLAG_STICKERS) != 0) {
            StreamingUtils.writeTLVector(stickers, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        this.caption = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_STICKERS) != 0) {
            stickers = StreamingUtils.readTLVector(stream, context, TLAbsInputDocument.class);
        }
    }

    public String toString() {
        return "inputMediaUploadedPhoto#630c9af1";
    }
}