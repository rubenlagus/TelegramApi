package org.telegram.api.functions.messages;

import org.telegram.api.messages.stickers.TLStickers;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get stickers.
 */
public class TLRequestMessagesGetStickers extends TLMethod<TLStickers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae22e045;

    private String emoticon;
    private String hash;

    /**
     * Instantiates a new TL request messages get stickers.
     */
    public TLRequestMessagesGetStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLStickers deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLStickers))
            return (TLStickers) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.stickers.TLStickers, got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.emoticon, stream);
        StreamingUtils.writeTLString(this.hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.emoticon = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "stickers.get#ae22e045";
    }
}