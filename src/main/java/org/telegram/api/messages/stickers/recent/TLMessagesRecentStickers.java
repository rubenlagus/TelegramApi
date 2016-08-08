package org.telegram.api.messages.stickers.recent;

import org.telegram.api.document.TLAbsDocument;
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
public class TLMessagesRecentStickers extends TLObject {
    public static final int CLASS_ID = 0x5ce20970;

    private int hash;
    private TLVector<TLAbsDocument> stickers;

    public TLMessagesRecentStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public TLVector<TLAbsDocument> getStickers() {
        return stickers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
        StreamingUtils.writeTLVector(stickers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
        stickers = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "messages.recentStickers#5ce20970";
    }
}
