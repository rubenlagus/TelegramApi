package org.telegram.api.message.media;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media document.
 */
public class TLMessageMediaDocument extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3e02ea8;

    private TLAbsDocument document;
    private String caption;

    /**
     * Instantiates a new TL message media document.
     */
    public TLMessageMediaDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets document.
     *
     * @return the document
     */
    public TLAbsDocument getDocument() {
        return this.document;
    }

    /**
     * Sets document.
     *
     * @param document the document
     */
    public void setDocument(TLAbsDocument document) {
        this.document = document;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.document, stream);
        StreamingUtils.writeTLString(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.document = ((TLAbsDocument) StreamingUtils.readTLObject(stream, context));
        this.caption = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageMediaDocument#f3e02ea8";
    }
}