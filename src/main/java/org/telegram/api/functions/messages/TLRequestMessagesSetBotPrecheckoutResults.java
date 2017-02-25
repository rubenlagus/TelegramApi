package org.telegram.api.functions.messages;

import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestMessagesSetBotPrecheckoutResults extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x9c2dd95;

    private static final int FLAG_ERROR       = 0x00000001; // 0
    private static final int FLAG_SUCCESS     = 0x00000002; // 1

    private int flags;
    private long queryId;
    private String error;

    public TLRequestMessagesSetBotPrecheckoutResults() {
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

    public boolean hasError() {
        return (flags & FLAG_ERROR) != 0;
    }

    public void setSuccess(boolean success) {
        if (success) {
            flags |= FLAG_ERROR;
        } else {
            flags &= ~FLAG_ERROR;
        }
    }

    public boolean isSuccess() {
        return (flags & FLAG_SUCCESS) != 0;
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
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        if (hasError()) {
            error = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.setBotPrecheckoutResults#9c2dd95";
    }
}
