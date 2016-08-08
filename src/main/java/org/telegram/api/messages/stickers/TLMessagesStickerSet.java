/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.messages.stickers;

import org.telegram.api.document.TLDocument;
import org.telegram.api.sticker.pack.TLStickerPack;
import org.telegram.api.sticker.set.TLStickerSet;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL stickers set.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLStickers
 * @date 9 /01/15
 */
public class TLMessagesStickerSet extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb60a24a6;

    /*
    messages.stickerSet#b60a24a6 set:StickerSet packs:Vector<StickerPack> documents:Vector<Document> = messages.StickerSet;
     */
    private TLStickerSet set;
    private TLVector<TLStickerPack> packs;
    private TLVector<TLDocument> documents;

    /**
     * Instantiates a new TL stickers.
     */
    public TLMessagesStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLStickerPack> getPacks() {
        return this.packs;
    }

    public void setPacks(TLVector<TLStickerPack> packs) {
        this.packs = packs;
    }

    public TLStickerSet getSet() {
        return this.set;
    }

    public void setSet(TLStickerSet set) {
        this.set = set;
    }

    /**
     * Gets documents.
     *
     * @return the documents
     */
    public TLVector<TLDocument> getDocuments() {
        return this.documents;
    }

    /**
     * Sets documents.
     *
     * @param documents the documents
     */
    public void setDocuments(TLVector<TLDocument> documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.set, stream);
        StreamingUtils.writeTLVector(this.packs, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.set = StreamingUtils.readTLObject(stream, context, TLStickerSet.class);
        this.packs = StreamingUtils.readTLVector(stream, context, TLStickerPack.class);
        this.documents = StreamingUtils.readTLVector(stream, context, TLDocument.class);
    }

    @Override
    public String toString() {
        return "stickersSet#b60a24a6";
    }
}
