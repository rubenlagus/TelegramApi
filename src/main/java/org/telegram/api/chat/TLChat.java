/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat;

import org.telegram.api.chat.photo.TLAbsChatPhoto;
import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Information of a chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChat extends TLAbsChat {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd91cdd54;

    private static final int FLAG_USER_CREATOR     = 0x00000001; // 0
    private static final int FLAG_USER_KICKED      = 0x00000002; // 1
    private static final int FLAG_USER_LEFT        = 0x00000004; // 2
    private static final int FLAG_ADMINS_ENABLED   = 0x00000008; // 3
    private static final int FLAG_USER_ADMIN       = 0x00000010; // 4
    private static final int FLAG_DEACTIVATED      = 0x00000020; // 5
    private static final int FLAG_MIGRATED_TO      = 0x00000040; // 6

    private int flags;
    private String title; ///< Title of the chat
    private TLAbsChatPhoto photo; ///< Chat profile photo
    private int participantsCount; ///< Number of participants in the chat
    private int date; ///< Creation date of the chat
    private int version; ///< Version of the chat
    private TLAbsInputChannel migratedTo; ///< Channel this group was moved to (supergroup)

    /**
     * Instantiates a new TL chat.
     */
    public TLChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public TLAbsChatPhoto getPhoto() {
        return this.photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
    }

    /**
     * Gets participants count.
     *
     * @return the participants count
     */
    public int getParticipantsCount() {
        return this.participantsCount;
    }

    /**
     * Sets participants count.
     *
     * @param participantsCount the participants count
     */
    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    public TLAbsInputChannel getMigratedTo() {
        return migratedTo;
    }

    public void setMigratedTo(TLAbsInputChannel migratedTo) {
        this.migratedTo = migratedTo;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isLeft() {
        return (flags & FLAG_USER_LEFT) != 0;
    }

    public boolean isForbidden() {
        return ((flags & FLAG_USER_KICKED) !=  0) || ((flags & FLAG_USER_LEFT) != 0);
    }

    public boolean isMigratedTo() {
        return ((flags & FLAG_MIGRATED_TO) != 0);
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeInt(this.participantsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.version, stream);
        if ((this.flags & FLAG_MIGRATED_TO) != 0) {
            StreamingUtils.writeTLObject(this.migratedTo, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsChatPhoto.class);
        this.participantsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_MIGRATED_TO) != 0) {
            this.migratedTo = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        }
    }

    public String toString() {
        return "chat#d91cdd54";
    }
}