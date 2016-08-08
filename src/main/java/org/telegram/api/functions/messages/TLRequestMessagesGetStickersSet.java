package org.telegram.api.functions.messages;

import org.telegram.api.input.sticker.set.TLAbsInputStickerSet;
import org.telegram.api.messages.stickers.TLMessagesStickerSet;
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
public class TLRequestMessagesGetStickersSet extends TLMethod<TLMessagesStickerSet> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2619a90e;

    private TLAbsInputStickerSet stickerSet;

    /**
     * Instantiates a new TL request messages get stickers.
     */
    public TLRequestMessagesGetStickersSet() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesStickerSet deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLMessagesStickerSet))
            return (TLMessagesStickerSet) res;
        throw new IOException("Incorrect response type. Expected " + TLMessagesStickerSet.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.stickerSet, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.stickerSet = (TLAbsInputStickerSet) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "stickers.getStickersSet#2619a90e";
    }
}