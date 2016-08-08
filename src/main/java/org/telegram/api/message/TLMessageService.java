package org.telegram.api.message;

import org.telegram.api.message.action.TLAbsMessageAction;
import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message service.
 */
public class TLMessageService extends TLAbsMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9e19a1f6;

    private static final int FLAG_UNUSED_0         = 0x00000001; // 0
    private static final int FLAG_OUT              = 0x00000002; // 1
    private static final int FLAG_UNUSED2          = 0x00000004; // 2
    private static final int FLAG_REPLY_TO_MSG_ID  = 0x00000008; // 3
    private static final int FLAG_MENTIONED        = 0x00000010; // 4
    private static final int FLAG_MEDIA_UNREAD     = 0x00000020; // 5
    private static final int FLAG_UNUSED7          = 0x00000040; // 6
    private static final int FLAG_UNUSED8          = 0x00000080; // 7
    private static final int FLAG_FROMID           = 0x00000100; // 8
    private static final int FLAG_UNUSED_9         = 0x00000200; // 9
    private static final int FLAG_UNUSED_10        = 0x00000400; // 10
    private static final int FLAG_UNUSED_11        = 0x00000800; // 11
    private static final int FLAG_UNUSED_12        = 0x00001000; // 12
    private static final int FLAG_SILENT           = 0x00002000; // 13
    private static final int FLAG_POST             = 0x00004000; // 14

    private int flags;
    private int id;
    private int fromId;
    private TLAbsPeer toId;
    private int replyToMessageId;
    private int date;
    private TLAbsMessageAction action;

    /**
     * Instantiates a new TL message service.
     */
    public TLMessageService() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * Gets action.
     *
     * @return the action
     */
    public TLAbsMessageAction getAction() {
        return this.action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(TLAbsMessageAction action) {
        this.action = action;
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
     * Gets to id.
     *
     * @return the to id
     */
    public TLAbsPeer getToId() {
        return this.toId;
    }

    @Override
    public int getChatId() {
        return getToId().getId();
    }

    /**
     * Sets to id.
     *
     * @param toId the to id
     */
    public void setToId(TLAbsPeer toId) {
        this.toId = toId;
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

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }

    public boolean hasFromId() {
        return (flags & FLAG_FROMID) != 0;
    }

    public boolean isPost() {
        return (this.flags & FLAG_POST) != 0;
    }

    public boolean isSilent() {
        return (this.flags & FLAG_SILENT) != 0;
    }


    public boolean isUnreadContent() {
        return (this.flags & FLAG_MEDIA_UNREAD) != 0;
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
        return (this.flags & FLAG_MENTIONED) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        if ((this.flags & FLAG_FROMID) != 0) {
            StreamingUtils.writeInt(this.fromId, stream);
        }
        StreamingUtils.writeTLObject(this.toId, stream);
        if ((this.flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            StreamingUtils.writeInt(this.replyToMessageId, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FROMID) != 0) {
            this.fromId = StreamingUtils.readInt(stream);
        }
        this.toId = ((TLAbsPeer) StreamingUtils.readTLObject(stream, context));
        if ((this.flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            this.replyToMessageId = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.action = ((TLAbsMessageAction) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messageService#9e19a1f6";
    }
}