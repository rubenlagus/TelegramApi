package org.telegram.api.functions.messages;

import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages import chat invite.
 */
public class TLRequestMessagesImportChatInvite extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6c50051c;

    private String hash;

    /**
     * Instantiates a new TL request messages import chat invite.
     */
    public TLRequestMessagesImportChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUpdates))
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
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
        return "request.importChatInvite#6c50051c";
    }
}