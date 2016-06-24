package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.blocked.TLAbsBlocked;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts get blocked.
 */
public class TLRequestContactsGetBlocked extends TLMethod<TLAbsBlocked> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf57c350f;

    private int offset;
    private int limit;

    /**
     * Instantiates a new TL request contacts get blocked.
     */
    public TLRequestContactsGetBlocked() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsBlocked deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsBlocked))
            return (TLAbsBlocked) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.blocked.TLAbsBlocked, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
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
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "contacts.getBlocked#f57c350f";
    }
}