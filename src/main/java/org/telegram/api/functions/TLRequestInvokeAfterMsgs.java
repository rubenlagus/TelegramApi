package org.telegram.api.functions;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request invoke after msgs.
 */
public class TLRequestInvokeAfterMsgs extends TLMethod<TLObject> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dc4b4f0;

    private TLLongVector msgIds;
    private TLMethod query;

    /**
     * Instantiates a new TL request invoke after msgs.
     */
    public TLRequestInvokeAfterMsgs() {
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
     * Gets msg ids.
     *
     * @return the msg ids
     */
    public TLLongVector getMsgIds() {
        return this.msgIds;
    }

    /**
     * Sets msg ids.
     *
     * @param value the value
     */
    public void setMsgIds(TLLongVector value) {
        this.msgIds = value;
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
        StreamingUtils.writeTLVector(this.msgIds, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.msgIds = StreamingUtils.readTLLongVector(stream, context);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    public String toString() {
        return "invokeAfterMsgs#3dc4b4f0";
    }
}