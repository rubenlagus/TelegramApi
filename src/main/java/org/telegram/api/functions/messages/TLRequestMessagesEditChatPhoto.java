package org.telegram.api.functions.messages;

import org.telegram.api.input.chat.photo.TLAbsInputChatPhoto;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages edit chat photo.
 */
public class TLRequestMessagesEditChatPhoto extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xca4c79d8;

    private int chatId;
    private TLAbsInputChatPhoto photo;

    /**
     * Instantiates a new TL request messages edit chat photo.
     */
    public TLRequestMessagesEditChatPhoto() {
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
     * Gets photo.
     *
     * @return the photo
     */
    public TLAbsInputChatPhoto getPhoto() {
        return this.photo;
    }

    /**
     * Sets photo.
     *
     * @param value the value
     */
    public void setPhoto(TLAbsInputChatPhoto value) {
        this.photo = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.photo = ((TLAbsInputChatPhoto) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.editChatPhoto#ca4c79d8";
    }
}