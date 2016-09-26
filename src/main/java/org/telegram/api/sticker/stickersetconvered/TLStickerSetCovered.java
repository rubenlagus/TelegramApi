package org.telegram.api.sticker.stickersetconvered;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.sticker.set.TLStickerSet;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLStickerSetCovered extends TLAbsStickerSetCovered {
    public static final int CLASS_ID = 0x6410a5d2;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(set, stream);
        StreamingUtils.writeTLObject(cover, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        set = StreamingUtils.readTLObject(stream, context, TLStickerSet.class);
        cover = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "stickerSetCovered#6410a5d2";
    }
}
