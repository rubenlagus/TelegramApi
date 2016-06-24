package org.telegram.api.functions.messages;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages add chat user.
 */
public class TLRequestMessagesAddChatUser extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf9a0aa09;

    private int chatId;
    private TLAbsInputUser userId;
    private int fwdLimit;

    /**
     * Instantiates a new TL request messages add chat user.
     */
    public TLRequestMessagesAddChatUser() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
    }

    /**
     * Gets fwd limit.
     *
     * @return the fwd limit
     */
    public int getFwdLimit() {
        return this.fwdLimit;
    }

    /**
     * Sets fwd limit.
     *
     * @param value the value
     */
    public void setFwdLimit(int value) {
        this.fwdLimit = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.fwdLimit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
        this.fwdLimit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.addChatUser#f9a0aa09";
    }
}