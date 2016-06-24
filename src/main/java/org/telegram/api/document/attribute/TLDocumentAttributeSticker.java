/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.api.input.sticker.set.TLAbsInputStickerSet;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates that the document is an sticker
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLDocumentAttributeSticker extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3a556302;

    private String alt; ///< Alternate text for the sticker
    private TLAbsInputStickerSet stickerset;
    /**
     * Instantiates a new TL document attribute sticker.
     */
    public TLDocumentAttributeSticker() {
        super();
    }


    /**
     * Gets alt.
     *
     * @return the alt
     */
    public String getAlt() {
        return this.alt;
    }

    /**
     * Sets alt.
     *
     * @param alt the alt
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    public TLAbsInputStickerSet getStickerset() {
        return this.stickerset;
    }

    public void setStickerset(TLAbsInputStickerSet stickerset) {
        this.stickerset = stickerset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.alt, stream);
        StreamingUtils.writeTLObject(this.stickerset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.alt = StreamingUtils.readTLString(stream);
        this.stickerset = (TLAbsInputStickerSet) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "documentAttributeSticker#3a556302";
    }
}
