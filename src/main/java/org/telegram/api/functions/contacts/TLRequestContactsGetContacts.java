package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.TLAbsContacts;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts get contacts.
 */
public class TLRequestContactsGetContacts extends TLMethod<TLAbsContacts> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x22c6aa08;

    private String hash;

    /**
     * Instantiates a new TL request contacts get contacts.
     */
    public TLRequestContactsGetContacts() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsContacts deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsContacts))
            return (TLAbsContacts) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLAbsContacts, got: " + res.getClass().getCanonicalName());
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
     * @param value the value
     */
    public void setHash(String value) {
        this.hash = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "contacts.getContacts#22c6aa08";
    }
}