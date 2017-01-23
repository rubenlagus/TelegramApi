package org.telegram.api.functions.messages;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.messages.chats.TLAbsMessagesChats;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesGetCommonChats extends TLMethod<TLAbsMessagesChats> {
    public static final int CLASS_ID = 0xd0a48c4;

    private TLAbsInputUser userId;
    private int maxId;
    private int limit;

    public TLRequestMessagesGetCommonChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsMessagesChats))
            return (TLAbsMessagesChats) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsMessagesChats.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.maxId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getCommonChats#d0a48c4";
    }
}