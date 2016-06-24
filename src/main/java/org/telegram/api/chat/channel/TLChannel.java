package org.telegram.api.chat.channel;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.chat.photo.TLAbsChatPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel information
 * @date 18 of September of 2015
 */
public class TLChannel extends TLAbsChat {
    public static final int CLASS_ID = 0xa14dca52;

    private static final int FLAG_CREATOR            = 0x00000001; // 0
    private static final int FLAG_KICKED             = 0x00000002; // 1
    private static final int FLAG_LEFT               = 0x00000004; // 2
    private static final int FLAG_EDITOR             = 0x00000008; // 3
    private static final int FLAG_MODERATOR          = 0x00000010; // 4
    private static final int FLAG_BROADCAST          = 0x00000020; // 5
    private static final int FLAG_USERNAME           = 0x00000040; // 6
    private static final int FLAG_VERIFIED           = 0x00000080; // 7
    private static final int FLAG_MEGAGROUP          = 0x00000100; // 8
    private static final int FLAG_RESTRICTED         = 0x00000200; // 9
    private static final int FLAG_INVITES_ENABLED    = 0x00000400; // 10
    private static final int FLAG_SIGNATURES         = 0x00000800; // 11
    private static final int FLAG_MIN                = 0x00001000; // 12
    private static final int FLAG_ACCESS_HASH        = 0x00002000; // 13

    private int flags;
    private long accessHash;
    private String title;
    private String username;
    private TLAbsChatPhoto photo;
    private int date;
    private int version;
    private String restrictionReason;

    public TLChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TLAbsChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestrictionReason() {
        return restrictionReason;
    }

    public void setRestrictionReason(String restrictionReason) {
        this.restrictionReason = restrictionReason;
    }

    public boolean hasUsername() {
        return (this.flags & FLAG_USERNAME) != 0;
    }

    public boolean hasRestrictionReason() {
        return (this.flags & FLAG_RESTRICTED) != 0;
    }

    public boolean hasAccessHash() {
        return (this.flags & FLAG_ACCESS_HASH) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(id, stream);
        if ((this.flags & FLAG_ACCESS_HASH) != 0) {
            StreamingUtils.writeLong(accessHash, stream);
        }
        StreamingUtils.writeTLString(title, stream);
        if ((this.flags & FLAG_USERNAME) != 0) {
            StreamingUtils.writeTLString(username, stream);
        }
        StreamingUtils.writeTLObject(photo, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(version, stream);
        if ((this.flags & FLAG_RESTRICTED) != 0) {
            StreamingUtils.writeTLString(restrictionReason, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_ACCESS_HASH) != 0) {
            this.accessHash = StreamingUtils.readLong(stream);
        }
        this.title = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_USERNAME) != 0) {
            this.username = StreamingUtils.readTLString(stream);
        }
        this.photo = (TLAbsChatPhoto) StreamingUtils.readTLObject(stream, context);
        this.date = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_RESTRICTED) != 0) {
            this.restrictionReason = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "channel.TLChannel#a14dca52";
    }
}
