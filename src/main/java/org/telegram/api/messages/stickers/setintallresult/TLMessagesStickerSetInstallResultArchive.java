package org.telegram.api.messages.stickers.setintallresult;

import org.telegram.api.sticker.stickersetconvered.TLAbsStickerSetCovered;
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

    private TLVector<TLAbsStickerSetCovered> sets;

    public TLMessagesStickerSetInstallResultArchive() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.stickerSetInstallResultArchive#35e410a8";
    }
}
