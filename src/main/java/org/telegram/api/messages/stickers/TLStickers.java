/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.messages.stickers;

import org.telegram.api.document.TLDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL stickers.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLStickers
 * @date 9 /01/15
 */
public class TLStickers extends TLAbsStickers {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8a8ecd32;

    private String hash;
    private TLVector<TLDocument> documents;

    /**
     * Instantiates a new TL stickers.
     */
    public TLStickers() {
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
    public String getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
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
        StreamingUtils.writeTLString(this.hash, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
        this.documents = StreamingUtils.readTLVector(stream, context, TLDocument.class);
    }

    @Override
    public String toString() {
        return "stickers#8a8ecd32";
    }
}
