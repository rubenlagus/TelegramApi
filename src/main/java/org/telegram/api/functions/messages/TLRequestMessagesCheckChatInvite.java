package org.telegram.api.functions.messages;

import org.telegram.api.chat.invite.TLAbsChatInvite;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages check chat invite.
 */
public class TLRequestMessagesCheckChatInvite extends TLMethod<TLAbsChatInvite> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3eadb1bb;

    private String hash;

    /**
     * Instantiates a new TL request messages check chat invite.
     */
    public TLRequestMessagesCheckChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsChatInvite deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsChatInvite))
            return (TLAbsChatInvite) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.chat.TLAbsChatInvite, got: " + res.getClass().getCanonicalName());
    }


    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "request.checkChatInvite#3eadb1bb";
    }
}