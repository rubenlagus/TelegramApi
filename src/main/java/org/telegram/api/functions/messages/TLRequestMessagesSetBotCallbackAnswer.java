package org.telegram.api.functions.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit admin
 */
public class TLRequestMessagesSetBotCallbackAnswer extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x130c2c85;

    private static final int FLAG_MESSAGE = 0x00000001;
    private static final int FLAG_ALERT   = 0x00000002;

    private int flags;
    private long queryId;
    private String message;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestMessagesSetBotCallbackAnswer() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() +", got: " + res.getClass().getName());
    }

    public int getFlags() {
        return flags;
    }

    public long getQueryId() {
        return queryId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(queryId, stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeTLString(message, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            message = StreamingUtils.readTLString(stream);
        }
    }

    public String toString() {
        return "messages.setBotCallbackAnswer#481c591a";
    }
}