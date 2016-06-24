package org.telegram.api.functions.messages;

import org.telegram.api.input.sticker.set.TLAbsInputStickerSet;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get stickers.
 */
public class TLRequestMessagesUninstallStickersSet extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf96e55de;

    private TLAbsInputStickerSet stickerSet;

    /**
     * Instantiates a new TL request messages get stickers.
     */
    public TLRequestMessagesUninstallStickersSet() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.TLBool, got: " + res.getClass().getCanonicalName());
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
        return "stickers.uninstallStickersSet#f96e55de";
    }
}