/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.api.document.attribute.TLAbsDocumentAttribute;
import org.telegram.api.photo.size.TLAbsPhotoSize;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * External document media content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessageMediaExternalDocument extends TLAbsDecryptedMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa95b0dd;

    private long id; ///< Document id
    private long accessHash; ///< Check sum, dependant on document ID
    private int date; ///< Creation date
    private String mimetype; ///< /// Document mimetype
    private int size; ///< Document size in bytes
    private TLAbsPhotoSize thumb; ///< ///< Document thumbnail
    private int dcId; ///< Datacenter where the document is stored
    private TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>(); ///< List of attributes of the document


    /**
     * Instantiates a new TL decrypted message media external document.
     */
    public TLDecryptedMessageMediaExternalDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets mimetype.
     *
     * @return the mimetype
     */
    public String getMimetype() {
        return this.mimetype;
    }

    /**
     * Sets mimetype.
     *
     * @param mimetype the mimetype
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets thumb.
     *
     * @return the thumb
     */
    public TLAbsPhotoSize getThumb() {
        return this.thumb;
    }

    /**
     * Sets thumb.
     *
     * @param thumb the thumb
     */
    public void setThumb(TLAbsPhotoSize thumb) {
        this.thumb = thumb;
    }

    /**
     * Gets dc id.
     *
     * @return the dc id
     */
    public int getDcId() {
        return this.dcId;
    }

    /**
     * Sets dc id.
     *
     * @param dcId the dc id
     */
    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return this.attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.mimetype, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLObject(this.thumb, stream);
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLVector(this.attributes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.mimetype = StreamingUtils.readTLString(stream);
        this.size = StreamingUtils.readInt(stream);
        this.thumb = (TLAbsPhotoSize) StreamingUtils.readTLObject(stream, context);
        this.dcId = StreamingUtils.readInt(stream);
        this.attributes = (TLVector<TLAbsDocumentAttribute>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "decryptedMessageMediaExternalDocument#fa95b0dd";
    }
}