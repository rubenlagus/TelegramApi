package org.telegram.api.functions.messages;

import org.telegram.api.messages.stickers.TLAbsAllStickers;
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
public class TLRequestMessagesGetMaskStickers extends TLMethod<TLAbsAllStickers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x65b8c79f;

    private int hash;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetMaskStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAllStickers deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsAllStickers)) {
            return (TLAbsAllStickers) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsAllStickers.class.getName() + ", got: " + res.getClass().getCanonicalName());
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
        return "messages.getMaskStickers#65b8c79f";
    }
}