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
    public static final int CLASS_ID = 0xd58f130a;

    private static final int FLAG_MESSAGE = 0x00000001; // 0
    private static final int FLAG_ALERT   = 0x00000002; // 1
    private static final int FLAG_URL     = 0x00000004; // 2

    private int flags;
    private long queryId;
    private String message;
    private String url;
    private int cacheTime;

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

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public long getQueryId() {
        return queryId;
    }

    public String getMessage() {
        return message;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public boolean hasAlert() {
        return (flags & FLAG_ALERT) != 0;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public void setMessage(String message) {
        flags |= FLAG_MESSAGE;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        flags |= FLAG_URL;
        this.url = url;
    }

    public boolean hasUrl() {
        return (flags & FLAG_URL) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(queryId, stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeTLString(message, stream);
        }
        if ((flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(url, stream);
        }
        StreamingUtils.writeInt(cacheTime, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            message = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_URL) != 0) {
            url = StreamingUtils.readTLString(stream);
        }
        cacheTime = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.setBotCallbackAnswer#d58f130a";
    }
}