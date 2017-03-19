package org.telegram.api.user;

import org.telegram.api.bot.TLBotInfo;
import org.telegram.api.contacts.TLContactsLink;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user full.
 */
public class TLUserFull extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf220f3f;

    private static final int FLAG_BLOCKED             = 0x00000001; // 0
    private static final int FLAG_ABOUT               = 0x00000002; // 1
    private static final int FLAG_PROFILE_PHOTO       = 0x00000004; // 2
    private static final int FLAG_BOT_INFO            = 0x00000008; // 3
    private static final int FLAG_CALLS_AVAILABLE     = 0x00000010; // 4
    private static final int FLAG_PHONE_CALLS_PRIVATE = 0x00000020; // 5

    private int flags;
    private TLAbsUser user;
    private String about;
    private TLContactsLink link;
    private TLAbsPhoto profilePhoto;
    private TLAbsPeerNotifySettings notifySettings;
    private TLBotInfo botInfo;
    private int commonChatsCount;

    /**
     * Instantiates a new TL user full.
     */
    public TLUserFull() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public TLAbsUser getUser() {
        return user;
    }

    public String getAbout() {
        return about;
    }

    public TLContactsLink getLink() {
        return link;
    }

    public TLAbsPhoto getProfilePhoto() {
        return profilePhoto;
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public TLBotInfo getBotInfo() {
        return botInfo;
    }

    public int getCommonChatsCount() {
        return commonChatsCount;
    }

    public boolean isBlocked() {
        return (flags & FLAG_BLOCKED) != 0;
    }

    public boolean hasPhoneCallsAvailable() {
        return (flags & FLAG_CALLS_AVAILABLE) != 0;
    }

    public boolean hasPhoneCallsPrivate() {
        return (flags & FLAG_PHONE_CALLS_PRIVATE) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(this.user, stream);
        if ((flags & FLAG_ABOUT) != 0) {
            StreamingUtils.writeTLString(about, stream);
        }
        StreamingUtils.writeTLObject(this.link, stream);
        if ((flags & FLAG_PROFILE_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.profilePhoto, stream);
        }
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        if ((flags & FLAG_BOT_INFO) != 0) {
            StreamingUtils.writeTLObject(this.botInfo, stream);
        }
        StreamingUtils.writeInt(commonChatsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        user = ((TLAbsUser) StreamingUtils.readTLObject(stream, context));
        if ((flags & FLAG_ABOUT) != 0) {
            about = StreamingUtils.readTLString(stream);
        }
        link = ((TLContactsLink) StreamingUtils.readTLObject(stream, context));
        if ((flags & FLAG_PROFILE_PHOTO) != 0) {
            profilePhoto = ((TLAbsPhoto) StreamingUtils.readTLObject(stream, context));
        }
        notifySettings = ((TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context));
        if ((flags & FLAG_BOT_INFO) != 0) {
            botInfo = (TLBotInfo) StreamingUtils.readTLObject(stream, context);
        }
        commonChatsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "userFull#f220f3f";
    }
}