package org.telegram.api.functions.messages;

import org.telegram.api.sticker.TLStickerSetCovered;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetUnusedStickers extends TLMethod<TLVector<TLStickerSetCovered>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4309d65b;

    private int limit;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetUnusedStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLStickerSetCovered> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLStickerSetCovered.class);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getUnusedStickers#4309d65b";
    }
}