package org.telegram.api.message;

import org.telegram.api.keyboard.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.api.message.media.TLMessageMediaEmpty;
import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message.
 */
public class TLMessage extends TLAbsMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc09be45f;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_OUT                = 0x00000002; // 1
    private static final int FLAG_FWD                = 0x00000004; // 2
    private static final int FLAG_REPLY              = 0x00000008; // 3
    private static final int FLAG_MENTION            = 0x00000010; // 4
    private static final int FLAG_MEDIA_UNREAD       = 0x00000020; // 5
    private static final int FLAG_REPLYKEYBOARD      = 0x00000040; // 6
    private static final int FLAG_ENTITIES           = 0x00000080; // 7
    private static final int FLAG_FROMID             = 0x00000100; // 8
    private static final int FLAG_MEDIA              = 0x00000200; // 9
    private static final int FLAG_VIEWS              = 0x00000400; // 10
    private static final int FLAG_VIA_BOT_ID         = 0x00000800; // 11
    private static final int FLAG_UNUSED_12          = 0x00001000; // 12
    private static final int FLAG_SILENT             = 0x00002000; // 13
    private static final int FLAG_POST               = 0x00004000; // 14
    private static final int FLAG_EDIT_DATE          = 0x00008000; // 15

    private int flags;
    private int id;
    private int fromId;
    private TLAbsPeer toId;
    private TLMessageFwdHeader fwdFrom;
    private int viaBotId;
    private int replyToMsgId;
    private int date;
    private String message;
    private TLAbsMessageMedia media;
    private TLAbsReplyMarkup replyMarkup;
    private TLVector<TLAbsMessageEntity> entities;
    private int views;
    private int editDate;

    /**
     * Instantiates a new TL message.
     */
    public TLMessage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * Gets media.
     *
     * @return the media
     */
    public TLAbsMessageMedia getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(TLAbsMessageMedia media) {
        this.media = media;
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
     * Gets to id.
     *
     * @return the to id
     */
    public TLAbsPeer getToId() {
        return this.toId;
    }

    /**
     * Sets to id.
     *
     * @param toId the to id
     */
    public void setToId(TLAbsPeer toId) {
        this.toId = toId;
    }

    @Override
    public int getChatId() {
        return getToId().getId();
    }

    /**
     * Gets from id.
     *
     * @return the from id
     */
    public int getFromId() {
        return this.fromId;
    }

    /**
     * Sets from id.
     *
     * @param fromId the from id
     */
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getEditDate() {
        return editDate;
    }

    public void setEditDate(int editDate) {
        this.editDate = editDate;
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

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return hasEntities() ? entities : new TLVector<>();
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        toggleEntities((entities != null) && !entities.isEmpty());
    }

    private void toggleEntities(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_ENTITIES;
        } else {
            this.flags &= ~FLAG_ENTITIES;
        }
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isPost() {
        return (this.flags & FLAG_POST) != 0;
    }

    public boolean isSilent() {
        return (this.flags & FLAG_SILENT) != 0;
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
        return (this.flags & FLAG_MEDIA_UNREAD) != 0;
    }

    public boolean isForwarded() {
        return (this.flags & FLAG_FWD) != 0;
    }

    public boolean isReply() {
        return (this.flags & FLAG_REPLY) != 0;
    }

    public boolean hasReplyKeyboard() {
        return (this.flags & FLAG_REPLYKEYBOARD) != 0;
    }

    public boolean hasEntities() {
        return (this.flags & FLAG_ENTITIES) != 0;
    }

    /**
     * Check if the message has text content
     * @return true if the message has text, false otherwise
     */
    public boolean hasText() {
        return (this.message == null) || !this.message.isEmpty();
    }

    /**
     * Check if the message has any media content
     * @return true if the message has media, false otherwise
     */
    public boolean hasMedia() {
        return ((this.flags & FLAG_MEDIA) != 0) && !(this.media instanceof TLMessageMediaEmpty);
    }

    public int getViaBotId() {
        return viaBotId;
    }

    public void setViaBotId(int viaBotId) {
        this.viaBotId = viaBotId;
    }

    public TLMessageFwdHeader getFwdFrom() {
        return fwdFrom;
    }

    public void setFwdFrom(TLMessageFwdHeader fwdFrom) {
        this.fwdFrom = fwdFrom;
    }

    public boolean hasFromId() {
        return (flags & FLAG_FROMID) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        if ((this.flags & FLAG_FROMID) != 0) {
            StreamingUtils.writeInt(this.fromId, stream);
        }
        StreamingUtils.writeTLObject(this.toId, stream);
        if ((this.flags & FLAG_FWD) != 0) {
            StreamingUtils.writeTLObject(this.fwdFrom, stream);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            StreamingUtils.writeInt(this.viaBotId, stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            StreamingUtils.writeTLObject(this.media, stream);
        }
        if ((this.flags & FLAG_REPLYKEYBOARD) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
        if ((this.flags & FLAG_VIEWS) != 0) {
            StreamingUtils.writeInt(this.views, stream);
        }
        if ((this.flags & FLAG_EDIT_DATE) != 0) {
            StreamingUtils.writeInt(this.editDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FROMID) != 0) {
            this.fromId = StreamingUtils.readInt(stream);
        }
        this.toId = ((TLAbsPeer) StreamingUtils.readTLObject(stream, context));
        if ((this.flags & FLAG_FWD) != 0) {
            this.fwdFrom = (TLMessageFwdHeader) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            this.viaBotId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            this.media = ((TLAbsMessageMedia) StreamingUtils.readTLObject(stream, context));
        }
        if ((this.flags & FLAG_REPLYKEYBOARD) != 0) {
            this.replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }
        if ((this.flags & FLAG_VIEWS) != 0) {
            this.views = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_EDIT_DATE) != 0) {
            this.editDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "message#c09be45f";
    }
}