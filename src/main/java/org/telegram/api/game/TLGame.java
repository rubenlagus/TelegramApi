package org.telegram.api.game;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 04 of October of 2016
 */
public class TLGame extends TLObject {
    public static final int CLASS_ID = 0xbdf9653b;

    private static final int FLAG_DOCUMENT    = 0x00000001; // 0

    private int flags;
    private long id;
    private long accessHash;
    private String shortName;
    private String title;
    private String description;
    private TLAbsPhoto photo;
    private TLAbsDocument document;

    public long getId() {
        return id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public String getShortName() {
        return shortName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public boolean hasDocument() {
        return (flags & FLAG_DOCUMENT) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeTLString(shortName, stream);
        StreamingUtils.writeTLString(title, stream);
        StreamingUtils.writeTLString(description, stream);
        StreamingUtils.writeTLObject(photo, stream);
        if ((flags & FLAG_DOCUMENT) != 0) {
            StreamingUtils.writeTLObject(document, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        shortName = StreamingUtils.readTLString(stream);
        title = StreamingUtils.readTLString(stream);
        description = StreamingUtils.readTLString(stream);
        photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        if ((flags & FLAG_DOCUMENT) != 0) {
            document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
    }

    @Override
    public String toString() {
        return "game#bdf9653b";
    }
}
