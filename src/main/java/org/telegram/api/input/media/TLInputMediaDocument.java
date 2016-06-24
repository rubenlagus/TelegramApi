package org.telegram.api.input.media;

import org.telegram.api.input.document.TLAbsInputDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media document.
 */
public class TLInputMediaDocument extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1a77f29c;

    /**
     * The Id.
     */
    protected TLAbsInputDocument id;
    private String caption;

    /**
     * Instantiates a new TL input media document.
     */
    public TLInputMediaDocument() {
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
    public TLAbsInputDocument getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLAbsInputDocument value) {
        this.id = value;
    }

    public String getCaption() {
        return caption;
    }

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
        this.id = ((TLAbsInputDocument) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaDocument#1a77f29c";
    }
}