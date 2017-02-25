package org.telegram.api.functions.messages;

import org.telegram.api.paymentapi.TLShippingOption;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestMessagesSetBotShippingResults extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0xe5f672fa;

    private static final int FLAG_ERROR              = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTIONS   = 0x00000002; // 1

    private int flags;
    private long queryId;
    private String error;
    private TLVector<TLShippingOption> shippingOptions;

    public TLRequestMessagesSetBotShippingResults() {
        super();
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        if (error == null) {
            flags &= ~FLAG_ERROR;
        } else {
            flags |= FLAG_ERROR;
        }
        this.error = error;
    }

    public TLVector<TLShippingOption> getShippingOptions() {
        return shippingOptions;
    }

    public void setShippingOptions(TLVector<TLShippingOption> shippingOptions) {
        if (shippingOptions == null) {
            flags &= ~FLAG_SHIPPING_OPTIONS;
        } else {
            flags |= FLAG_SHIPPING_OPTIONS;
        }
        this.shippingOptions = shippingOptions;
    }

    public boolean hasError() {
        return (flags & FLAG_ERROR) != 0;
    }

    public boolean hasShippingOptions() {
        return (flags & FLAG_SHIPPING_OPTIONS) != 0;
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(queryId, stream);
        if (hasError()) {
            StreamingUtils.writeTLString(error, stream);
        }
        if (hasShippingOptions()) {
            StreamingUtils.writeTLVector(shippingOptions, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        if (hasError()) {
            error = StreamingUtils.readTLString(stream);
        }
        if (hasShippingOptions()) {
            shippingOptions = StreamingUtils.readTLVector(stream, context, TLShippingOption.class);
        }
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.setBotShippingResults#e5f672fa";
    }
}
