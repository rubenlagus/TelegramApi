package org.telegram.api.update;

import org.telegram.api.sendmessage.action.TLAbsSendMessageAction;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update user typing.
 */
public class TLUpdateUserTyping extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5c486927;

    private int userId;
    private TLAbsSendMessageAction action;

    /**
     * Instantiates a new TL update user typing.
     */
    public TLUpdateUserTyping() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TLAbsSendMessageAction getAction() {
        return this.action;
    }

    public void setAction(TLAbsSendMessageAction action) {
        this.action = action;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.action = (TLAbsSendMessageAction) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "updateUserTyping#5c486927";
    }
}
