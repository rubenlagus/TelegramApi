package org.telegram.api.message.action;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action chat edit photo.
 */
public class TLMessageActionChatEditPhoto extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7fcb13a8;

    private TLAbsPhoto photo;

    /**
     * Instantiates a new TL message action chat edit photo.
     */
    public TLMessageActionChatEditPhoto() {
        super();
    }

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.photo, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.photo = ((TLAbsPhoto) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messageActionChatEditPhoto#7fcb13a8";
    }
}