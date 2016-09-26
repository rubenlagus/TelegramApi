/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.api.input.sticker.set.TLAbsInputStickerSet;
import org.telegram.api.sticker.TLMaskCoords;
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
    public static final int CLASS_ID = 0x6319d612;

    private static final int FLAG_COORDS    = 0x00000001; // 0
    private static final int FLAG_MASK      = 0x00000002; // 1

    private int flags;
    private String alt; ///< Alternate text for the sticker
    private TLAbsInputStickerSet stickerset;
    private TLMaskCoords maskCoords;
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

    public TLMaskCoords getMaskCoords() {
        return maskCoords;
    }

    public boolean isMask() {
        return (flags & FLAG_MASK) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(alt, stream);
        StreamingUtils.writeTLObject(stickerset, stream);
        if ((flags & FLAG_COORDS) != 0) {
            StreamingUtils.writeTLObject(stickerset, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        alt = StreamingUtils.readTLString(stream);
        stickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        if ((flags & FLAG_COORDS) != 0) {
            maskCoords = StreamingUtils.readTLObject(stream, context, TLMaskCoords.class);
        }
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "documentAttributeSticker#6319d612";
    }
}
