package org.telegram.api.functions.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages discard encryption.
 */
public class TLRequestMessagesDiscardEncryption extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedd923c5;

    private int chatId;

    /**
     * Instantiates a new TL request messages discard encryption.
     */
    public TLRequestMessagesDiscardEncryption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return this.chatId;
    }

    /**
     * Sets chat id.
     *
     * @param value the value
     */
    public void setChatId(int value) {
        this.chatId = value;
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
        return "messages.discardEncryption#edd923c5";
    }
}