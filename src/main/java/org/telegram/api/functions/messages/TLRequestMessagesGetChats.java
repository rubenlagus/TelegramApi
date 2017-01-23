package org.telegram.api.functions.messages;

import org.telegram.api.messages.chats.TLMessagesChats;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetChats extends TLMethod<TLMessagesChats> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3c6aa187;

    private TLIntVector id;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesChats deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesChats)) {
            return (TLMessagesChats) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "messages.getChats#3c6aa187";
    }
}