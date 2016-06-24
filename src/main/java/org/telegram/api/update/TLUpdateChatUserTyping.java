package org.telegram.api.update;

import org.telegram.api.sendmessage.action.TLAbsSendMessageAction;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat user typing.
 */
public class TLUpdateChatUserTyping extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9a65ea1f;

    private int chatId;
    private TLAbsSendMessageAction action;
    private int userId;

    /**
     * Instantiates a new TL update chat user typing.
     */
    public TLUpdateChatUserTyping() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public TLAbsSendMessageAction getAction() {
        return action;
    }

    public int getUserId() {
        return userId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.action = (TLAbsSendMessageAction) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "updateChatUserTyping#9a65ea1f";
    }
}