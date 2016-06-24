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
 * The type TL request messages get all stickers.
 */
public class TLRequestMessagesGetAllStickers extends TLMethod<TLAbsAllStickers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c9618b1;

    /**
     * The Hash.
     */
    protected int hash;

    /**
     * Instantiates a new TL request messages get all stickers.
     */
    public TLRequestMessagesGetAllStickers() {
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
        throw new IOException("Incorrect response type. Expected " + TLAbsAllStickers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public int getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.hash = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getAllStickers#1c9618b1";
    }
}