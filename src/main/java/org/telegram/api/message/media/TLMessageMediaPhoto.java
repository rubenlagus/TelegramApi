package org.telegram.api.message.media;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media photo.
 */
public class TLMessageMediaPhoto extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3d8ce53d;

    private TLAbsPhoto photo;
    private String caption;

    /**
     * Instantiates a new TL message media photo.
     */
    public TLMessageMediaPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public TLAbsPhoto getPhoto() {
        return this.photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
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

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeTLString(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.photo = ((TLAbsPhoto) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageMediaPhoto#3d8ce53d";
    }
}