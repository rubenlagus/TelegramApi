package org.telegram.api.updates.channel.differences;

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
 * The type TL updates channel differences too long.
 */
public class TLUpdatesChannelDifferencesTooLong extends TLAbsUpdatesChannelDifferences {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x410dee07;

    private static final int FLAG_FINAL = 0x00000001; // 0
    private static final int FLAG_TIMEOUT = 0x00000002; // 1

    private int topMessage;
    private int readInboxMaxId;
    private int readOutboxMaxId;
    private int unreadCount;
    private TLVector<TLAbsMessage> messages;
    private TLVector<TLAbsChat> chats;
    private TLVector<TLAbsUser> users;


    /**
     * Instantiates a new TL updates channel differences too long.
     */
    public TLUpdatesChannelDifferencesTooLong() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getTopMessage() {
        return topMessage;
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

    public TLVector<TLAbsMessage> getMessages() {
        return messages;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            StreamingUtils.writeInt(timeout, stream);
        }
        StreamingUtils.writeInt(topMessage, stream);
        StreamingUtils.writeInt(readInboxMaxId, stream);
        StreamingUtils.writeInt(readOutboxMaxId, stream);
        StreamingUtils.writeInt(unreadCount, stream);
        StreamingUtils.writeTLVector(messages, stream);
        StreamingUtils.writeTLVector(chats, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            this.timeout = StreamingUtils.readInt(stream);
        }
        this.topMessage = StreamingUtils.readInt(stream);
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.readOutboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    public String toString() {
        return "updates.channelDifferenceTooLong#410dee07";
    }
}