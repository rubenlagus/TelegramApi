package org.telegram.api.channel.participants;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participant creator
 * @date 18 of September of 2015
 */
public class TLChannelParticipantCreator extends TLAbsChannelParticipant {
    public static final int CLASS_ID = 0xe3e2e1f9;

    private int userId;

    public TLChannelParticipantCreator() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channel.participants.TLChannelParticipantCreator#e3e2e1f9";
    }
}
