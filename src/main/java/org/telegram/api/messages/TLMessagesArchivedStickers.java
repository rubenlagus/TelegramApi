package org.telegram.api.messages;

import org.telegram.api.sticker.stickersetconvered.TLAbsStickerSetCovered;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLMessagesArchivedStickers extends TLObject {
    public static final int CLASS_ID = 0x4fcba9c8;

    private int count;
    private TLVector<TLAbsStickerSetCovered> sets;

    public TLMessagesArchivedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        count = StreamingUtils.readInt(stream);
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.archivedStickers#4fcba9c8";
    }
}
