package org.telegram.api.user;

import org.telegram.api.user.profile.photo.TLAbsUserProfilePhoto;
import org.telegram.api.user.status.TLAbsUserStatus;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * User information
 */
public class TLUser extends TLAbsUser {
    public static final int CLASS_ID = 0x2e13f4c3;

    private static final int FLAG_ACCESS_HASH           = 0x00000001; // 0
    private static final int FLAG_FIRST_NAME            = 0x00000002; // 1
    private static final int FLAG_LAST_NAME             = 0x00000004; // 2
    private static final int FLAG_USERNAME              = 0x00000008; // 3
    private static final int FLAG_PHONE                 = 0x00000010; // 4
    private static final int FLAG_PHOTO                 = 0x00000020; // 5
    private static final int FLAG_STATUS                = 0x00000040; // 6
    private static final int FLAG_UNUSED                = 0x00000080; // 7
    private static final int FLAG_UNUSED2               = 0x00000100; // 8
    private static final int FLAG_UNUSED3               = 0x00000200; // 9
    private static final int FLAG_SELF                  = 0x00000400; // 10
    private static final int FLAG_CONTACT               = 0x00000800; // 11
    private static final int FLAG_MUTUAL_CONTACT        = 0x00001000; // 12
    private static final int FLAG_DELETED               = 0x00002000; // 13
    private static final int FLAG_BOT                   = 0x00004000; // 14
    private static final int FLAG_BOT_READING_HISTORY   = 0x00008000; // 15
    private static final int FLAG_BOT_CANT_JOIN_GROUP   = 0x00010000; // 16
    private static final int FLAG_VERIFIED              = 0x00020000; // 17
    private static final int FLAG_RESTRICTED            = 0x00040000; // 18
    private static final int FLAG_INLINE_PLACEHOLDER    = 0x00080000; // 19
    private static final int FLAG_MIN                   = 0x00100000; // 20
    private static final int FLAG_BOT_INLINE_GEO        = 0x00200000; // 21
    private static final int FLAG_LANG_CODE             = 0x00400000; // 22

    private int flags;
    private long accessHash;
    private String firstName = "";
    private String lastName = "";
    private String userName = "";
    private String phone = "";
    private TLAbsUserProfilePhoto photo;
    private TLAbsUserStatus status;
    private int botInfoVersion;
    private String restrictionReason;
    private String botInlinePlaceholder;
    private String langCode;

    public TLUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return this.flags;
    }

    public long getAccessHash() {
        return this.accessHash;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPhone() {
        return this.phone;
    }

    public TLAbsUserProfilePhoto getPhoto() {
        return this.photo;
    }

    public TLAbsUserStatus getStatus() {
        return this.status;
    }

    public int getBotInfoVersion() {
        return this.botInfoVersion;
    }

    public String getRestrictionReason() {
        return restrictionReason;
    }

    public String getBotInlinePlaceholder() {
        return botInlinePlaceholder;
    }

    public String getLangCode() {
        return langCode;
    }

    public boolean hasAccessHash() {
        return (this.flags & FLAG_ACCESS_HASH) != 0;
    }

    public boolean hasPhone() {
        return (this.flags & FLAG_PHONE) != 0;
    }

    public boolean hasUserName() {
        return (this.flags & FLAG_USERNAME) != 0;
    }

    public boolean hasFirstName() {
        return (this.flags & FLAG_FIRST_NAME) != 0;
    }

    public boolean hasLastName() {
        return (this.flags & FLAG_LAST_NAME) != 0;
    }

    public boolean isSelf() {
        return (this.flags & FLAG_SELF) != 0;
    }

    public boolean isRestricted() {
        return (this.flags & FLAG_RESTRICTED) != 0;
    }

    public boolean isContact() {
        return ((this.flags & FLAG_CONTACT) != 0) || isMutualContact();
    }

    public boolean isMutualContact() {
        return (this.flags & FLAG_MUTUAL_CONTACT) != 0;
    }

    public boolean isDeleted() {
        return (this.flags & FLAG_DELETED) != 0;
    }

    public boolean isBot() {
        return (this.flags & FLAG_BOT) != 0;
    }

    public boolean isBotReadingHistory() {
        return (this.flags & FLAG_BOT_READING_HISTORY) != 0;
    }

    public boolean isBotCantAddToGroup() {
        return (this.flags & FLAG_BOT_CANT_JOIN_GROUP) != 0;
    }

    public boolean isVerified() {
        return (this.flags & FLAG_VERIFIED) != 0;
    }

    public boolean isInlineBot() {
        return (this.flags & FLAG_INLINE_PLACEHOLDER) != 0;
    }

    public boolean hasLangCode() {
        return (this.flags & FLAG_LANG_CODE) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);

        if ((this.flags & FLAG_ACCESS_HASH) != 0) {
            StreamingUtils.writeLong(this.accessHash, stream);
        }
        if ((this.flags & FLAG_FIRST_NAME) != 0) {
            StreamingUtils.writeTLString(this.firstName, stream);
        }
        if ((this.flags & FLAG_LAST_NAME) != 0) {
            StreamingUtils.writeTLString(this.lastName, stream);
        }
        if ((this.flags & FLAG_USERNAME) != 0) {
            StreamingUtils.writeTLString(this.userName, stream);
        }
        if ((this.flags & FLAG_PHONE) != 0) {
            StreamingUtils.writeTLString(this.phone, stream);
        }
        if ((this.flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if ((this.flags & FLAG_STATUS) != 0) {
            StreamingUtils.writeTLObject(this.status, stream);
        }
        if ((this.flags & FLAG_BOT) != 0) {
            StreamingUtils.writeInt(this.botInfoVersion, stream);
        }
        if ((this.flags & FLAG_RESTRICTED) != 0) {
            StreamingUtils.writeTLString(this.restrictionReason, stream);
        }
        if ((this.flags & FLAG_INLINE_PLACEHOLDER) != 0) {
            StreamingUtils.writeTLString(this.botInlinePlaceholder, stream);
        }
        if ((this.flags & FLAG_LANG_CODE) != 0) {
            StreamingUtils.writeTLString(this.langCode, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_ACCESS_HASH) != 0) {
            this.accessHash = StreamingUtils.readLong(stream);
        }
        if ((this.flags & FLAG_FIRST_NAME) != 0) {
            this.firstName = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_LAST_NAME) != 0) {
            this.lastName = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_USERNAME) != 0) {
            this.userName = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_PHONE) != 0) {
            this.phone = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_PHOTO) != 0) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsUserProfilePhoto.class);
        }
        if ((this.flags & FLAG_STATUS) != 0) {
            this.status = StreamingUtils.readTLObject(stream, context, TLAbsUserStatus.class);
        }
        if ((this.flags & FLAG_BOT) != 0) {
            this.botInfoVersion = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_RESTRICTED) != 0) {
            this.restrictionReason = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_INLINE_PLACEHOLDER) != 0) {
            this.botInlinePlaceholder = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_LANG_CODE) != 0) {
            this.langCode = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "user.TLUser#2e13f4c3";
    }
}
