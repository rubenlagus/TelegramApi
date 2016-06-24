package org.telegram.api.input.chat.photo;

import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.api.input.photo.crop.TLAbsInputPhotoCrop;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input chat uploaded photo.
 */
public class TLInputChatUploadedPhoto extends TLAbsInputChatPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x94254732;

    /**
     * The Crop.
     */
    protected TLAbsInputPhotoCrop crop;
    /**
     * The File.
     */
    protected TLAbsInputFile file;

    /**
     * Instantiates a new TL input chat uploaded photo.
     */
    public TLInputChatUploadedPhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
        StreamingUtils.writeTLObject(this.crop, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.file = ((TLAbsInputFile) StreamingUtils.readTLObject(stream, context));
        this.crop = ((TLAbsInputPhotoCrop) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "inputChatUploadedPhoto#94254732";
    }
}