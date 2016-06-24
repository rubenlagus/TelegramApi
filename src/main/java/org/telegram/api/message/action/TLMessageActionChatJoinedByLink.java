package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action chat joined by link.
 */
public class TLMessageActionChatJoinedByLink extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf89cf5e8;

    private int inviterId;

    /**
     * Instantiates a new TL message action chat joined by link.
     */
    public TLMessageActionChatJoinedByLink() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets inviter id.
     *
     * @return the inviter id
     */
    public int getInviterId() {
        return this.inviterId;
    }

    /**
     * Sets inviter id.
     *
     * @param inviterId the inviter id
     */
    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.inviterId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.inviterId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messageaction.messageActionChatJoinedByLink#f89cf5e8";
    }
}