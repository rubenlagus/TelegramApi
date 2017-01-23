package org.telegram.api.functions.channels;

import org.telegram.api.messages.chats.TLMessagesChats;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request channel get channels
 */
public class TLRequestChannelsGetAdminedPublicChannels extends TLMethod<TLMessagesChats> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8d8d82d7;

    /**
     * Instantiates a new TL request channel get channels
     */
    public TLRequestChannelsGetAdminedPublicChannels() {
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
        throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
    }

    public String toString() {
        return "channels.getAdminedPublicChannels#8d8d82d7";
    }
}