package org.telegram.api.functions;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request invoke without updates.
 */
public class TLRequestInvokeWithoutUpdates extends TLMethod<TLObject> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf9459b7;

    private TLMethod query;

    /**
     * Instantiates a new TL request invoke without updates.
     */
    public TLRequestInvokeWithoutUpdates() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLObject deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public TLMethod getQuery() {
        return this.query;
    }

    /**
     * Sets query.
     *
     * @param value the value
     */
    public void setQuery(TLMethod value) {
        this.query = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    public String toString() {
        return "invokeWithoutUpdates#bf9459b7";
    }
}