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
    public static final int CLASS_ID = 0x5932fc03;

    private static final int FLAG_BLOCKED         = 0x00000001; // 0
    private static final int FLAG_ABOUT           = 0x00000002; // 1
    private static final int FLAG_PROFILE_PHOTO   = 0x00000004; // 2
    private static final int FLAG_BOT_INFO        = 0x00000008; // 3

    private int flags;
    private TLAbsUser user;
    private String about;
    private TLContactsLink link;
    private TLAbsPhoto profilePhoto;
    private TLAbsPeerNotifySettings notifySettings;
    private TLBotInfo botInfo;

    /**
     * Instantiates a new TL user full.
     */
    public TLUserFull() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public TLAbsUser getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param value the value
     */
    public void setUser(TLAbsUser value) {
        this.user = value;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public TLContactsLink getLink() {
        return this.link;
    }

    /**
     * Sets link.
     *
     * @param value the value
     */
    public void setLink(TLContactsLink value) {
        this.link = value;
    }

    /**
     * Gets profile photo.
     *
     * @return the profile photo
     */
    public TLAbsPhoto getProfilePhoto() {
        return this.profilePhoto;
    }

    /**
     * Sets profile photo.
     *
     * @param value the value
     */
    public void setProfilePhoto(TLAbsPhoto value) {
        this.profilePhoto = value;
    }

    /**
     * Gets notify settings.
     *
     * @return the notify settings
     */
    public TLAbsPeerNotifySettings getNotifySettings() {
        return this.notifySettings;
    }

    /**
     * Sets notify settings.
     *
     * @param value the value
     */
    public void setNotifySettings(TLAbsPeerNotifySettings value) {
        this.notifySettings = value;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public TLBotInfo getBotInfo() {
        return this.botInfo;
    }

    public void setBotInfo(TLBotInfo botInfo) {
        this.botInfo = botInfo;
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
    }

    public String toString() {
        return "userFull#5932fc03";
    }
}