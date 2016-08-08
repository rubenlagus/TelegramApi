/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.messages.stickers;

import org.telegram.api.sticker.set.TLStickerSet;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL all stickers.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAllStickers
 * @date 9 /01/15
 */
public class TLAllStickers extends TLAbsAllStickers {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedfd405f;

    private int hash;
    private TLVector<TLStickerSet> sets = new TLVector<>();

    /**
     * Instantiates a new TL all stickers.
     */
    public TLAllStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public int getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    public TLVector<TLStickerSet> getSets() {
        return this.sets;
    }

    public void setSets(TLVector<TLStickerSet> sets) {
        this.sets = sets;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.sets = StreamingUtils.readTLVector(stream, context, TLStickerSet.class);
    }

    @Override
    public String toString() {
        return "allStickers#edfd405f";
    }
}
