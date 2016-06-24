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
    public static final int CLASS_ID = 0x5e167646;

    private static final int FLAG_FINAL = 0x00000001; // 0
    private static final int FLAG_TIMEOUT = 0x00000002; // 1

    private int topMessage;
    private int topImportantMessage;
    private int readInboxMaxId;
    private int unreadCount;
    private int unreadImportantCount;
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

    public void setTopMessage(int topMessage) {
        this.topMessage = topMessage;
    }

    public int getTopImportantMessage() {
        return topImportantMessage;
    }

    public void setTopImportantMessage(int topImportantMessage) {
        this.topImportantMessage = topImportantMessage;
    }

    public int getReadInboxMaxId() {
        return readInboxMaxId;
    }

    public void setReadInboxMaxId(int readInboxMaxId) {
        this.readInboxMaxId = readInboxMaxId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public int getUnreadImportantCount() {
        return unreadImportantCount;
    }

    public void setUnreadImportantCount(int unreadImportantCount) {
        this.unreadImportantCount = unreadImportantCount;
    }

    public TLVector<TLAbsMessage> getMessages() {
        return messages;
    }

    public void setMessages(TLVector<TLAbsMessage> messages) {
        this.messages = messages;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            StreamingUtils.writeInt(timeout, stream);
        }
        StreamingUtils.writeInt(topMessage, stream);
        StreamingUtils.writeInt(topImportantMessage, stream);
        StreamingUtils.writeInt(readInboxMaxId, stream);
        StreamingUtils.writeInt(unreadCount, stream);
        StreamingUtils.writeInt(unreadImportantCount, stream);
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
        this.topImportantMessage = StreamingUtils.readInt(stream);
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.unreadImportantCount = StreamingUtils.readInt(stream);
        this.messages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "updates.TLUpdatesChannelDifferencesTooLong#5e167646";
    }
}