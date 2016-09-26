package org.telegram.api.messages.stickers.featured;

import org.telegram.api.sticker.stickersetconvered.TLAbsStickerSetCovered;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
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
public class TLMessagesFeaturedStickers extends TLAbsMessagesFeaturedStickers {
    public static final int CLASS_ID = 0xf89d88e5;

    private int hash;
    private TLVector<TLAbsStickerSetCovered> sets;
    private TLLongVector unread;

    public TLMessagesFeaturedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    public TLLongVector getUnread() {
        return unread;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
        StreamingUtils.writeTLVector(sets, stream);
        StreamingUtils.writeTLVector(unread, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
        unread = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.featuredStickers#f89d88e5";
    }
}
