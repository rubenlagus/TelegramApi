package org.telegram.api.updates.channel.differences;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL updates channel differences.
 */
public class TLUpdatesChannelDifferences extends TLAbsUpdatesChannelDifferences {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2064674e;

    private static final int FLAG_FINAL   = 0x00000001; // 0
    private static final int FLAG_TIMEOUT = 0x00000002; // 1

    private TLVector<TLAbsMessage> newMessages;
    private TLVector<TLAbsUpdate> otherUpdates;
    private TLVector<TLAbsChat> chats;
    private TLVector<TLAbsUser> users;


    /**
     * Instantiates a new TL updates channel differences.
     */
    public TLUpdatesChannelDifferences() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsMessage> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(TLVector<TLAbsMessage> newMessages) {
        this.newMessages = newMessages;
    }

    public TLVector<TLAbsUpdate> getOtherUpdates() {
        return otherUpdates;
    }

    public void setOtherUpdates(TLVector<TLAbsUpdate> otherUpdates) {
        this.otherUpdates = otherUpdates;
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

    public boolean isFinal() {
        return (flags & FLAG_FINAL) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if ((this.flags & FLAG_TIMEOUT) != 0) {
            StreamingUtils.writeInt(timeout, stream);
        }
        StreamingUtils.writeTLVector(newMessages, stream);
        StreamingUtils.writeTLVector(otherUpdates, stream);
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
        this.newMessages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.otherUpdates = StreamingUtils.readTLVector(stream, context, TLAbsUpdate.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    public String toString() {
        return "updates.channelDifference#2064674e";
    }
}