package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.TLResolvedPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts resolve username.
 */
public class TLRequestContactsResolveUsername extends TLMethod<TLResolvedPeer> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf93ccba3;

    private String username;

    /**
     * Instantiates a new TL request contacts resolve username.
     */
    public TLRequestContactsResolveUsername() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLResolvedPeer deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLResolvedPeer)) {
            return (TLResolvedPeer) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLResolvedPeer.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.username, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.username = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "contacts.resolveUsername#f93ccba3";
    }
}