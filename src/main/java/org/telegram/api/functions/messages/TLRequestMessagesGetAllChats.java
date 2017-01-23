package org.telegram.api.functions.messages;

import org.telegram.api.messages.chats.TLAbsMessagesChats;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesGetAllChats extends TLMethod<TLAbsMessagesChats> {
    public static final int CLASS_ID = 0xeba80ff0;

    private TLIntVector exceptIds;

    public TLRequestMessagesGetAllChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsMessagesChats)) {
            return (TLAbsMessagesChats) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsMessagesChats.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLIntVector getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(TLIntVector exceptIds) {
        this.exceptIds = exceptIds;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.exceptIds, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.exceptIds = StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "messages.getAllChats#eba80ff0";
    }
}