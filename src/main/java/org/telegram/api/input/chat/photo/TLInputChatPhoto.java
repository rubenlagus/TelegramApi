package org.telegram.api.input.chat.photo;

import org.telegram.api.input.photo.TLAbsInputPhoto;
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
    public static final int CLASS_ID = 0x8953ad37;

    private TLAbsInputPhoto id;

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputPhoto.class);
    }

    public String toString() {
        return "inputChatPhoto#8953ad37";
    }
}