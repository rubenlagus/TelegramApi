package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.TLContactsFound;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts search.
 */
public class TLRequestContactsSearch extends TLMethod<TLContactsFound> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11f812d8;

    private String q;
    private int limit;

    /**
     * Instantiates a new TL request contacts search.
     */
    public TLRequestContactsSearch() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLContactsFound deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLContactsFound))
            return (TLContactsFound) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLFound, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets q.
     *
     * @return the q
     */
    public String getQ() {
        return this.q;
    }

    /**
     * Sets q.
     *
     * @param value the value
     */
    public void setQ(String value) {
        this.q = value;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Sets limit.
     *
     * @param value the value
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.q = StreamingUtils.readTLString(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "contacts.search#11f812d8";
    }
}