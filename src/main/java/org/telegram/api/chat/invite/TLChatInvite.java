/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.invite;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invitation to a chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatInvite extends TLAbsChatInvite {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdb74f558;

    private static final int FLAG_CHANNEL      = 0x00000001; // 0
    private static final int FLAG_BROADCAST    = 0x00000002; // 1
    private static final int FLAG_PUBLIC       = 0x00000004; // 2
    private static final int FLAG_MEGAGROUP    = 0x00000008; // 3
    private static final int FLAG_PARTICIPANTS = 0x00000010; // 4

    private int flags;
    private String title; ///< Title of the chat
    private TLAbsPhoto photo;
    private int participantsCount;
    private TLVector<TLAbsUser> participants;

    /**
     * Instantiates a new TL chat invite.
     */
    public TLChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getTitle() {
        return title;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public TLVector<TLAbsUser> getParticipants() {
        return participants;
    }

    public boolean isChannel() {
        return (flags & FLAG_CHANNEL) != 0;
    }

    public boolean isBroadcast() {
        return (flags & FLAG_BROADCAST) != 0;
    }

    public boolean isPublic() {
        return (flags & FLAG_PUBLIC) != 0;
    }

    public boolean isMegagroup() {
        return (flags & FLAG_MEGAGROUP) != 0;
    }

    public boolean hasParticipants() {
        return (flags & FLAG_PARTICIPANTS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLObject(photo, stream);
        StreamingUtils.writeInt(participantsCount, stream);
        if ((flags & FLAG_PARTICIPANTS) != 0) {
            StreamingUtils.writeTLVector(participants, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        photo = (TLAbsPhoto) StreamingUtils.readTLObject(stream, context);
        participantsCount = StreamingUtils.readInt(stream);
        if ((flags & FLAG_PARTICIPANTS) != 0) {
            participants = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        }
    }

    @Override
    public String toString() {
        return "chatInvite#db74f558";
    }
}
