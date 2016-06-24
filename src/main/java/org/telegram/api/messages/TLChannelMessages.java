package org.telegram.api.messages;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.message.TLMessageGroup;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages.
 */
public class TLChannelMessages extends TLAbsMessages {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc0f17bc;

    private static final int FLAG_COLLAPSED  = 0x00000001; // 0

    private int flags;
    private int pts;
    private int count;
    private TLVector<TLMessageGroup> collapsed;

    /**
     * Instantiates a new TL messages.
     */
    public TLChannelMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TLVector<TLMessageGroup> getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(TLVector<TLMessageGroup> collapsed) {
        this.collapsed = collapsed;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(pts, stream);
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        if ((this.flags & FLAG_COLLAPSED) != 0) {
            StreamingUtils.writeTLVector(this.collapsed, stream);
        }
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        pts = StreamingUtils.readInt(stream);
        count = StreamingUtils.readInt(stream);
        this.messages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        if ((this.flags & FLAG_COLLAPSED) != 0) {
            this.collapsed = (TLVector<TLMessageGroup>) StreamingUtils.readTLVector(stream, context);
        }
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "messages.TLChannelMessages#bc0f17bc";
    }
}