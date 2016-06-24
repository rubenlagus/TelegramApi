/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicate the size of an image document
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLDocumentAttributeImageSize extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6c37c15c;

    private int w; ///< Width in pixels
    private int h; ///< Height in pixels

    /**
     * Instantiates a new TL document attribute image size.
     */
    public TLDocumentAttributeImageSize() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return this.w;
    }

    /**
     * Sets w.
     *
     * @param w the w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return this.h;
    }

    /**
     * Sets h.
     *
     * @param h the h
     */
    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeImageSize#6c37c15c";
    }
}
