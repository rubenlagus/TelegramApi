package org.telegram.api.functions.help;

import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestHelpSetBotUpdatesStatus extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0xec22cfcd;

    private int pendingUpdatesCount;
    private String message;

    public TLRequestHelpSetBotUpdatesStatus() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(pendingUpdatesCount, stream);
        StreamingUtils.writeTLString(message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        pendingUpdatesCount = StreamingUtils.readInt(stream);
        message = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.setBotUpdatesStatus#ec22cfcd";
    }
}