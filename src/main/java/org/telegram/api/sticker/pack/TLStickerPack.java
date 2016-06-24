/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.sticker.pack;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sticker pack.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLStickerPack
 * @date 9 /01/15
 */
public class TLStickerPack extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12b299d4;

    private String emoticon;
    private TLLongVector documents;

    /**
     * Instantiates a new TL sticker pack.
     */
    public TLStickerPack() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets emoticon.
     *
     * @return the emoticon
     */
    public String getEmoticon() {
        return this.emoticon;
    }

    /**
     * Sets emoticon.
     *
     * @param emoticon the emoticon
     */
    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    /**
     * Gets documents.
     *
     * @return the documents
     */
    public TLLongVector getDocuments() {
        return this.documents;
    }

    /**
     * Sets documents.
     *
     * @param documents the documents
     */
    public void setDocuments(TLLongVector documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.emoticon, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.emoticon = StreamingUtils.readTLString(stream);
        this.documents = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "stickerPack#12b299d4";
    }
}
