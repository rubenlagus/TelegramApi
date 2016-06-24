package org.telegram.api.channel;

import org.telegram.api.channel.participants.TLAbsChannelParticipant;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief channel participants
 * @date 24 of September of 2015
 */
public class TLChannelParticipants extends TLObject{
    public static final int CLASS_ID = 0xf56ee2a8;

    private int count;
    private TLVector<TLAbsChannelParticipant> participants;
    private TLVector<TLAbsUser> users;

    public TLChannelParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TLVector<TLAbsChannelParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(TLVector<TLAbsChannelParticipant> participants) {
        this.participants = participants;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(participants, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.participants = (TLVector<TLAbsChannelParticipant>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "channel.TLChannelParticipants#f56ee2a8";
    }
}
