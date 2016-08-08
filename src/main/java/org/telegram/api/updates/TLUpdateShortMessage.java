package org.telegram.api.updates;

import org.telegram.api.message.TLMessageFwdHeader;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update short message.
 */
public class TLUpdateShortMessage extends TLAbsUpdates {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x914fbf11;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_OUT                = 0x00000002; // 1
    private static final int FLAG_FWD                = 0x00000004; // 2
    private static final int FLAG_REPLY              = 0x00000008; // 3
    private static final int FLAG_MENTION            = 0x00000010; // 4
    private static final int FLAG_CONTENT_UNREAD     = 0x00000020; // 5
    private static final int FLAG_UNUSED6            = 0x00000040; // 6
    private static final int FLAG_ENTITIES           = 0x00000080; // 7
    private static final int FLAG_UNUSED8            = 0x00000100; // 8
    private static final int FLAG_UNUSED9            = 0x00000200; // 9
    private static final int FLAG_UNUSED10           = 0x00000400; // 10
    private static final int FLAG_VIA_BOT_ID         = 0x00000800; // 11
    private static final int FLAG_UNUSED_12          = 0x00001000; // 12
    private static final int FLAG_SILENT             = 0x00002000; // 13

    private int flags;
    private int id;
    private int userId;
    private String message = "";
    private int pts;
    private int ptsCount;
    private int date;
    private TLMessageFwdHeader fwdFrom;
    private int viaBotId;
    private int replyToMsgId;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL update short message.
     */
    public TLUpdateShortMessage() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets from id.
     *
     * @return the from id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets from id.
     *
     * @param userId the from id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
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
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public TLMessageFwdHeader getFwdFrom() {
        return fwdFrom;
    }

    public void setFwdFrom(TLMessageFwdHeader fwdFrom) {
        this.fwdFrom = fwdFrom;
    }

    /**
     * Gets reply to msg id.
     *
     * @return the reply to msg id
     */
    public int getReplyToMsgId() {
        return this.replyToMsgId;
    }

    /**
     * Sets reply to msg id.
     *
     * @param replyToMsgId the reply to msg id
     */
    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return hasEntities() ? entities : new TLVector<>();
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    public int getViaBotId() {
        return viaBotId;
    }

    public void setViaBotId(int viaBotId) {
        this.viaBotId = viaBotId;
    }

    /**
     * Check if the message has text content
     * @return true if the message has text, false otherwise
     */
    public boolean hasText() {
        return (this.message == null) || !this.message.isEmpty();
    }

    public boolean isForwarded() {
        return (this.flags & FLAG_FWD) != 0;
    }

    public boolean isReply() {
        return (this.flags & FLAG_REPLY) != 0;
    }

    /**
     * Is sent.
     *
     * @return the boolean
     */
    public boolean isSent() {
        return (this.flags & FLAG_OUT) != 0;
    }

    public boolean isMention() {
        return (this.flags & FLAG_MENTION) != 0;
    }

    public boolean isUnreadContent() {
        return (this.flags & FLAG_CONTENT_UNREAD) != 0;
    }

    public boolean hasEntities() {
        return (this.flags & FLAG_ENTITIES) != 0;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        if ((this.flags & FLAG_FWD) != 0) {
            StreamingUtils.writeTLObject(this.fwdFrom, stream);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            StreamingUtils.writeInt(this.viaBotId, stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FWD) != 0) {
            this.fwdFrom = (TLMessageFwdHeader) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            this.viaBotId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }
    }

    public String toString() {
        return "updateShortMessage#914fbf11";
    }
}