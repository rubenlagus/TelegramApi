package org.telegram.api.functions.messages;

import org.telegram.api.messages.TLMessagesChatFull;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get full chat.
 */
public class TLRequestMessagesGetFullChat extends TLMethod<TLMessagesChatFull> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3b831c66;

    private int chatId;

    /**
     * Instantiates a new TL request messages get full chat.
     */
    public TLRequestMessagesGetFullChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesChatFull deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesChatFull)) {
            return (TLMessagesChatFull) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getFullChat#3b831c66";
    }
}