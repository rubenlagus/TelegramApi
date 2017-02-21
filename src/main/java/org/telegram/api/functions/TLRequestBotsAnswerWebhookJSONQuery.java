package org.telegram.api.functions;

import org.telegram.api.TLDataJSON;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestBotsAnswerWebhookJSONQuery extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0xe6213f4d;

    private long queryId;
    private TLDataJSON data;

    public TLRequestBotsAnswerWebhookJSONQuery() {
        super();
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public TLDataJSON getData() {
        return data;
    }

    public void setData(TLDataJSON data) {
        this.data = data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(queryId, stream);
        StreamingUtils.writeTLObject(data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        queryId = StreamingUtils.readLong(stream);
        data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "bots.answerWebhookJSONQuery#e6213f4d";
    }
}
