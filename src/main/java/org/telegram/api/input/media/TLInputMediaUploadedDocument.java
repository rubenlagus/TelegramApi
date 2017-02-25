package org.telegram.api.input.media;

import org.telegram.api.document.attribute.TLAbsDocumentAttribute;
import org.telegram.api.input.document.TLAbsInputDocument;
import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media uploaded document.
 */
public class TLInputMediaUploadedDocument extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd070f1e9;

    private static final int FLAG_STICKERS = 0x00000001; // 0

    private int flags;
    private TLAbsInputFile file;
    private String mimeType;
    private TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
    private String caption;
    private TLVector<TLAbsInputDocument> stickers;

    /**
     * Instantiates a new TL input media uploaded document.
     */
    public TLInputMediaUploadedDocument() {
        super();
        mimeType = "";
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
     * @param file the file
     */
    public void setFile(TLAbsInputFile file) {
        this.file = file;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return this.attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param mimeType the mime type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCaption() {
        return caption;
    }

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
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeTLObject(this.attributes, stream);
        StreamingUtils.writeTLString(this.caption, stream);
        if ((flags & FLAG_STICKERS) != 0) {
            StreamingUtils.writeTLVector(stickers, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.file = ((TLAbsInputFile) StreamingUtils.readTLObject(stream, context));
        this.mimeType = StreamingUtils.readTLString(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
        this.caption = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_STICKERS) != 0) {
            stickers = StreamingUtils.readTLVector(stream, context, TLAbsInputDocument.class);
        }
    }

    public String toString() {
        return "inputMediaUploadedDocument#d070f1e9";
    }
}