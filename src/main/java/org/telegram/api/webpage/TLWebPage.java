/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.webpage;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.page.TLAbsPage;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL web page.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLWebPage
 * @date 9 /01/15
 */
public class TLWebPage extends TLAbsWebPage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5f07b4bc;

    private static final int FLAG_TYPE               = 0x00000001; // 0
    private static final int FLAG_SITENAME           = 0x00000002; // 1
    private static final int FLAG_TITLE              = 0x00000004; // 2
    private static final int FLAG_DESCRIPTION        = 0x00000008; // 3
    private static final int FLAG_PHOTO              = 0x00000010; // 4
    private static final int FLAG_URL                = 0x00000020; // 5
    private static final int FLAG_SIZE               = 0x00000040; // 6
    private static final int FLAG_DURATION           = 0x00000080; // 7
    private static final int FLAG_AUTHOR             = 0x00000100; // 8
    private static final int FLAG_DOCUMENT           = 0x00000200; // 9
    private static final int FLAG_CACHED             = 0x00000400; // 10

    private int flags;
    private long id;
    private String url;
    private String display_url;
    private int hash;
    private String type;
    private String site_name;
    private String title;
    private String description;
    private TLAbsPhoto photo;
    private String embed_url;
    private String embed_type;
    private int embed_width;
    private int embed_height;
    private int duration;
    private String author;
    private TLAbsDocument document;
    private TLAbsPage cachedPage;

    /**
     * Instantiates a new TL web page.
     */
    public TLWebPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public int getHash() {
        return hash;
    }

    public String getType() {
        return type;
    }

    public String getSite_name() {
        return site_name;
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

    public String getEmbed_url() {
        return embed_url;
    }

    public String getEmbed_type() {
        return embed_type;
    }

    public int getEmbed_width() {
        return embed_width;
    }

    public int getEmbed_height() {
        return embed_height;
    }

    public int getDuration() {
        return duration;
    }

    public String getAuthor() {
        return author;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public TLAbsPage getCachedPage() {
        return cachedPage;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLString(this.display_url, stream);
        StreamingUtils.writeInt(this.hash, stream);
        if ((this.flags & FLAG_TYPE) != 0) {
            StreamingUtils.writeTLString(this.type, stream);
        }
        if ((this.flags & FLAG_SITENAME) != 0) {
            StreamingUtils.writeTLString(this.site_name, stream);
        }
        if ((this.flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if ((this.flags & FLAG_DESCRIPTION) != 0) {
            StreamingUtils.writeTLString(this.description, stream);
        }
        if ((this.flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if ((this.flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(this.embed_url, stream);
            StreamingUtils.writeTLString(this.embed_type, stream);
        }
        if ((this.flags & FLAG_SIZE) != 0) {
            StreamingUtils.writeInt(this.embed_width, stream);
            StreamingUtils.writeInt(this.embed_height, stream);
        }
        if ((this.flags & FLAG_DURATION) != 0) {
            StreamingUtils.writeInt(this.duration, stream);
        }
        if ((this.flags & FLAG_AUTHOR) != 0) {
            StreamingUtils.writeTLString(this.author, stream);
        }
        if ((this.flags & FLAG_DOCUMENT) != 0) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if ((flags & FLAG_CACHED) != 0) {
            StreamingUtils.writeTLObject(this.cachedPage, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.url = StreamingUtils.readTLString(stream);
        this.display_url = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_TYPE) != 0) {
            this.type = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_SITENAME) != 0) {
            this.site_name = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_TITLE) != 0) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_DESCRIPTION) != 0) {
            this.description = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_PHOTO) != 0) {
            this.photo = (TLAbsPhoto) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_URL) != 0) {
            this.embed_url = StreamingUtils.readTLString(stream);
            this.embed_type = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_SIZE) != 0) {
            this.embed_width = StreamingUtils.readInt(stream);
            this.embed_height = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_DURATION) != 0) {
            this.duration = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_AUTHOR) != 0) {
            this.author = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_DOCUMENT) != 0) {
            this.document = (TLAbsDocument) StreamingUtils.readTLObject(stream, context);
        }
        if ((flags & FLAG_CACHED) != 0) {
            cachedPage = StreamingUtils.readTLObject(stream, context, TLAbsPage.class);
        }
    }

    @Override
    public String toString() {
        return "webPage#5f07b4bc";
    }
}
