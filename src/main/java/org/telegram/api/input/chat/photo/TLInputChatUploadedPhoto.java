package org.telegram.api.input.chat.photo;

import org.telegram.api.input.file.TLAbsInputFile;
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
    public static final int CLASS_ID = 0x927c55b4;

    /**
     * The File.
     */
    private TLAbsInputFile file;

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
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
    }

    public String toString() {
        return "inputChatUploadedPhoto#927c55b4";
    }
}