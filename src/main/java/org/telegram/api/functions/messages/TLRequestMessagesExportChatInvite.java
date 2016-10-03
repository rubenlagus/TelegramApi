package org.telegram.api.functions.messages;

import org.telegram.api.chat.invite.TLChatInviteExported;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages export chat invite.
 */
public class TLRequestMessagesExportChatInvite extends TLMethod<TLChatInviteExported> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7d885289;

    private int chatId;

    /**
     * Instantiates a new TL request messages export chat invite.
     */
    public TLRequestMessagesExportChatInvite() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLChatInviteExported deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLChatInviteExported)) {
            return (TLChatInviteExported) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLChatInviteExported.class.getName() + ", got: " + res.getClass().getCanonicalName());
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
        return "request.exportChatInvite#7d885289";
    }
}