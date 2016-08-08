/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.document;

import org.telegram.api.document.attribute.TLAbsDocumentAttribute;
import org.telegram.api.photo.size.TLAbsPhotoSize;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent a document
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLDocument extends TLAbsDocument {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x87232bc7;

    private long accessHash; ///< Check sum, dependant on document ID
    private int date; ///< Creation date
    private String mimeType; /// Document mimetype
    private int size; ///< Document size in bytes
    private TLAbsPhotoSize thumb; ///< Document thumbnail
    private int dcId; ///< Datacenter where the document is stored
    private int version;
    private TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>(); ///< List of attributes of the document

    /**
     * Instantiates a new TL document.
     */
    public TLDocument() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public int getDate() {
        return date;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getSize() {
        return size;
    }

    public TLAbsPhotoSize getThumb() {
        return thumb;
    }

    public int getDcId() {
        return dcId;
    }

    public int getVersion() {
        return version;
    }

    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return attributes;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLObject(this.thumb, stream);
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeTLVector(this.attributes, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.size = StreamingUtils.readInt(stream);
        this.thumb = ((TLAbsPhotoSize) StreamingUtils.readTLObject(stream, context));
        this.dcId = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "document#87232bc7";
    }
}