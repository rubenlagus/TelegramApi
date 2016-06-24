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
 * The type TL request messages delete chat user.
 */
public class TLRequestMessagesDeleteChatUser extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0611f16;

    private int chatId;
    private TLAbsInputUser userId;

    /**
     * Instantiates a new TL request messages delete chat user.
     */
    public TLRequestMessagesDeleteChatUser() {
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.deleteChatUser#e0611f16";
    }
}