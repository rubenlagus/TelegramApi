package org.telegram.api.functions.messages;

import org.telegram.api.input.encrypted.TLInputEncryptedChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages set encrypted typing.
 */
public class TLRequestMessagesSetEncryptedTyping extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x791451ed;

    private TLInputEncryptedChat peer;
    private boolean typing;

    /**
     * Instantiates a new TL request messages set encrypted typing.
     */
    public TLRequestMessagesSetEncryptedTyping() {
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
     * Gets peer.
     *
     * @return the peer
     */
    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    /**
     * Gets typing.
     *
     * @return the typing
     */
    public boolean getTyping() {
        return this.typing;
    }

    /**
     * Sets typing.
     *
     * @param value the value
     */
    public void setTyping(boolean value) {
        this.typing = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLBool(this.typing, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLInputEncryptedChat) StreamingUtils.readTLObject(stream, context));
        this.typing = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "messages.setEncryptedTyping#791451ed";
    }
}