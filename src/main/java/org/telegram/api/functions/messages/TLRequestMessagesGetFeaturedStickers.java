package org.telegram.api.functions.messages;

import org.telegram.api.messages.stickers.featured.TLAbsMessagesFeaturedStickers;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetFeaturedStickers extends TLMethod<TLAbsMessagesFeaturedStickers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2dacca4f;

    private int hash;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetFeaturedStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessagesFeaturedStickers deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsMessagesFeaturedStickers)) {
            return (TLAbsMessagesFeaturedStickers) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsMessagesFeaturedStickers.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        hash = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getFeaturedStickers#2dacca4f";
    }
}