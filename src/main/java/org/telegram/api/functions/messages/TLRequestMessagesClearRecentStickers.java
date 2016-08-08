package org.telegram.api.functions.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesClearRecentStickers extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xab02e5d2;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesClearRecentStickers() {
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
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "messages.clearRecentStickers#ab02e5d2";
    }
}