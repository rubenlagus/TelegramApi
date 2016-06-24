package org.telegram.api.update;

import org.telegram.api.chat.participant.chatparticipants.TLAbsChatParticipants;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat participants.
 */
public class TLUpdateChatParticipants extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7761198;

    private TLAbsChatParticipants participants;

    /**
     * Instantiates a new TL update chat participants.
     */
    public TLUpdateChatParticipants() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public TLAbsChatParticipants getParticipants() {
        return this.participants;
    }

    /**
     * Sets participants.
     *
     * @param participants the participants
     */
    public void setParticipants(TLAbsChatParticipants participants) {
        this.participants = participants;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.participants, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.participants = ((TLAbsChatParticipants) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "updateChatParticipants#7761198";
    }
}