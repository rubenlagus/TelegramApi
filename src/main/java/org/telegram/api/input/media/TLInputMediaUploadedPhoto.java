package org.telegram.api.input.media;

import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media uploaded photo.
 */
public class TLInputMediaUploadedPhoto extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf7aff1c0;

    private TLAbsInputFile file;
    private String caption;

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
        StreamingUtils.writeTLString(this.caption, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.file = ((TLAbsInputFile) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaUploadedPhoto#f7aff1c0";
    }
}