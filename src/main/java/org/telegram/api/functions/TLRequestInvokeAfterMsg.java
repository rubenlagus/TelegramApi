package org.telegram.api.functions;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request invoke after msg.
 */
public class TLRequestInvokeAfterMsg extends TLMethod<TLObject> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcb9f372d;

    private long msgId;
    private TLMethod query;

    /**
     * Instantiates a new TL request invoke after msg.
     */
    public TLRequestInvokeAfterMsg() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    /**
     * Gets msg id.
     *
     * @return the msg id
     */
    public long getMsgId() {
        return this.msgId;
    }

    /**
     * Sets msg id.
     *
     * @param value the value
     */
    public void setMsgId(long value) {
        this.msgId = value;
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

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.msgId, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.msgId = StreamingUtils.readLong(stream);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public String toString() {
        return "invokeAfterMsg#cb9f372d";
    }
}