package org.telegram.api.chat.channel;

import org.telegram.api.bot.TLBotInfo;
import org.telegram.api.chat.TLAbsChatFull;
import org.telegram.api.chat.invite.TLAbsChatInvite;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel full information
 * @date 18 of September of 2015
 */
public class TLChannelFull extends TLAbsChatFull {
    public static final int CLASS_ID = 0xc3d5512f;

    private static final int FLAG_PARTICIPANTS            = 0x00000001; // 0
    private static final int FLAG_ADMIN                   = 0x00000002; // 1
    private static final int FLAG_KICKED                  = 0x00000004; // 2
    private static final int FLAG_CAN_VIEW_PARTICIPANTS   = 0x00000008; // 3
    private static final int FLAG_MIGRATED                = 0x00000010; // 4
    private static final int FLAG_PINNED_MESSAGE          = 0x00000020; // 5
    private static final int FLAG_CAN_SET_USERNAME        = 0x00000040; // 6

    private int flags;
    private String about;
    private int participantsCount;
    private int adminCount;
    private int kickedCount;
    private int readInboxMaxId;
    private int readOutboxMaxId;
    private int unreadCount;
    private TLVector<TLBotInfo> botInfo;
    private int migratedFromChatId;
    private int migratedFromMaxId;
    private int pinnedMessageId;

    public TLChannelFull() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getAbout() {
        return about;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public int getAdminCount() {
        return adminCount;
    }

    public int getKickedCount() {
        return kickedCount;
    }

    public int getReadInboxMaxId() {
        return readInboxMaxId;
    }

    public int getReadOutboxMaxId() {
        return readOutboxMaxId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public TLVector<TLBotInfo> getBotInfo() {
        return botInfo;
    }

    public int getMigratedFromChatId() {
        return migratedFromChatId;
    }

    public int getMigratedFromMaxId() {
        return migratedFromMaxId;
    }

    public int getPinnedMessageId() {
        return pinnedMessageId;
    }

    public boolean canViewParticipants() {
        return (flags & FLAG_CAN_VIEW_PARTICIPANTS) != 0;
    }

    public boolean canSetUsername() {
        return (flags & FLAG_CAN_SET_USERNAME) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeTLString(about, stream);
        if ((this.flags & FLAG_PARTICIPANTS) != 0) {
            StreamingUtils.writeInt(participantsCount, stream);
        }
        if ((this.flags & FLAG_ADMIN) != 0) {
            StreamingUtils.writeInt(adminCount, stream);
        }
        if ((this.flags & FLAG_KICKED) != 0) {
            StreamingUtils.writeInt(kickedCount, stream);
        }
        StreamingUtils.writeInt(readInboxMaxId, stream);
        StreamingUtils.writeInt(readOutboxMaxId, stream);
        StreamingUtils.writeInt(unreadCount, stream);
        StreamingUtils.writeTLObject(photo, stream);
        StreamingUtils.writeTLObject(notifySettings, stream);
        StreamingUtils.writeTLObject(exportedInvite, stream);
        StreamingUtils.writeTLVector(botInfo, stream);
        if ((this.flags & FLAG_MIGRATED) != 0) {
            StreamingUtils.writeInt(migratedFromChatId, stream);
            StreamingUtils.writeInt(migratedFromMaxId, stream);
        }
        if ((this.flags & FLAG_PINNED_MESSAGE) != 0) {
            StreamingUtils.writeInt(pinnedMessageId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.about = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_PARTICIPANTS) != 0) {
            this.participantsCount = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_ADMIN) != 0) {
            this.adminCount = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_KICKED) != 0) {
            this.kickedCount = StreamingUtils.readInt(stream);
        }
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.readOutboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.photo = (TLAbsPhoto) StreamingUtils.readTLObject(stream, context);
        this.notifySettings = (TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context);
        this.exportedInvite = (TLAbsChatInvite) StreamingUtils.readTLObject(stream, context);
        this.botInfo = (TLVector<TLBotInfo>) StreamingUtils.readTLVector(stream, context);
        if ((this.flags & FLAG_MIGRATED) != 0) {
            this.migratedFromChatId = StreamingUtils.readInt(stream);
            this.migratedFromMaxId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_PINNED_MESSAGE) != 0) {
            this.pinnedMessageId = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "channelFull#c3d5512f";
    }
}
