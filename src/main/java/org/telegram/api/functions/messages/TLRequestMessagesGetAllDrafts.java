package org.telegram.api.functions.messages;

import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetAllDrafts extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6a3f8d65;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetAllDrafts() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "messages.getAllDrafts#6a3f8d65";
    }
}