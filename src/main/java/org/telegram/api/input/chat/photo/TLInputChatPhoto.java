package org.telegram.api.input.chat.photo;

import org.telegram.api.input.photo.TLAbsInputPhoto;
import org.telegram.api.input.photo.crop.TLAbsInputPhotoCrop;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input chat photo.
 */
public class TLInputChatPhoto extends TLAbsInputChatPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb2e1bf08;

    private TLAbsInputPhoto id;
    private TLAbsInputPhotoCrop crop;

    /**
     * Instantiates a new TL input chat photo.
     */
    public TLInputChatPhoto() {
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
     * @param id the id
     */
    public void setId(TLAbsInputPhoto id) {
        this.id = id;
    }

    /**
     * Gets crop.
     *
     * @return the crop
     */
    public TLAbsInputPhotoCrop getCrop() {
        return this.crop;
    }

    /**
     * Sets crop.
     *
     * @param crop the crop
     */
    public void setCrop(TLAbsInputPhotoCrop crop) {
        this.crop = crop;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLObject(this.crop, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = ((TLAbsInputPhoto) StreamingUtils.readTLObject(stream, context));
        this.crop = ((TLAbsInputPhotoCrop) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "inputChatPhoto#b2e1bf08";
    }
}