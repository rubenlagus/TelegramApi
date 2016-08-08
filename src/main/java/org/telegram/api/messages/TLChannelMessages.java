package org.telegram.api.messages;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
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
    public static final int CLASS_ID = 0x99262e37;

    private int flags;
    private int pts;
    private int count;

    /**
     * Instantiates a new TL messages.
     */
    public TLChannelMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(pts, stream);
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        pts = StreamingUtils.readInt(stream);
        count = StreamingUtils.readInt(stream);
        this.messages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "messages.channelMessages#99262e37";
    }
}