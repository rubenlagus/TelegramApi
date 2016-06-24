package org.telegram.api.input.media;

import org.telegram.api.input.photo.TLAbsInputPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaPhoto extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe9bfb4f3;

    private TLAbsInputPhoto id;
    private String caption;

    /**
     * Instantiates a new TL input media photo.
     */
    public TLInputMediaPhoto() {
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
    public TLAbsInputPhoto getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLAbsInputPhoto value) {
        this.id = value;
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
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLString(this.caption, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = ((TLAbsInputPhoto) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaPhoto#e9bfb4f3";
    }
}