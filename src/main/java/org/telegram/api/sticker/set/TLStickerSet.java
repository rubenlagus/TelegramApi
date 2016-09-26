package org.telegram.api.sticker.set;

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
 * @date 07 of July of 2015
 */
public class TLStickerSet extends TLObject {
    public static final int CLASS_ID = 0xcd303b41;

    private static final int FLAG_INSTALLED      = 0x00000001; // 0
    private static final int FLAG_ARCHIVED       = 0x00000002; // 1
    private static final int FLAG_OFFICIAL       = 0x00000004; // 2
    private static final int FLAG_MASK           = 0x00000008; // 3

    private int flags;
    private long id;
    private long accessHash;
    private String title;
    private String shortName;
    private int count;
    private int hash;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public String getTitle() {
        return title;
    }

    public String getShortName() {
        return shortName;
    }

    public int getCount() {
        return count;
    }

    public int getHash() {
        return hash;
    }

    public boolean isInstalled() {
        return (flags & FLAG_INSTALLED) != 0;
    }

    public boolean isArchived() {
        return (flags & FLAG_ARCHIVED) != 0;
    }

    public boolean isOffcial() {
        return (flags & FLAG_OFFICIAL) != 0;
    }

    public boolean isMask() {
        return (flags & FLAG_MASK) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.shortName, stream);
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.shortName = StreamingUtils.readTLString(stream);
        this.count = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "stickerSet#cd303b41";
    }
}
