package org.telegram.api.messages.stickers.setintallresult;

import org.telegram.api.sticker.TLStickerSetCovered;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 08 of August of 2016
 */
public class TLMessagesStickerSetInstallResultArchive extends TLAbsMessagesStickerSetInstallResult {
    public static final int CLASS_ID = 0x35e410a8;

    private TLVector<TLStickerSetCovered> sets;

    public TLMessagesStickerSetInstallResultArchive() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLStickerSetCovered> getSets() {
        return sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        sets = StreamingUtils.readTLVector(stream, context, TLStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.stickerSetInstallResultArchive#35e410a8";
    }
}
