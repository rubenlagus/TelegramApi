package org.telegram.api.channel.participants;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participant Kicked
 * @date 18 of September of 2015
 */
public class TLChannelParticipantKicked extends TLAbsChannelParticipant {
    public static final int CLASS_ID = 0x8cc5e69a;

    private int userId;
    private int kickedBy;
    private int date;

    public TLChannelParticipantKicked() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getKickedBy() {
        return kickedBy;
    }

    public void setKickedBy(int kickedBy) {
        this.kickedBy = kickedBy;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(userId, stream);
        StreamingUtils.writeInt(kickedBy, stream);
        StreamingUtils.writeInt(date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.kickedBy = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channel.participants.TLChannelParticipantKicked#8cc5e69a";
    }
}
