package org.telegram.api.toppeer;

import org.telegram.api.toppeer.category.TLAbsTopPeerCategory;
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
public class TLTopPeerCategoryPeers extends TLObject {
    public static final int CLASS_ID = 0xfb834291;

    private TLAbsTopPeerCategory category;
    private int count;
    private TLVector<TLTopPeer> peers;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsTopPeerCategory getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }

    public TLVector<TLTopPeer> getPeers() {
        return peers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(category, stream);
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(peers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        category = StreamingUtils.readTLObject(stream, context, TLAbsTopPeerCategory.class);
        count = StreamingUtils.readInt(stream);
        peers = StreamingUtils.readTLVector(stream, context, TLTopPeer.class);
    }

    @Override
    public String toString() {
        return "topPeerCategoryPeers#fb834291";
    }
}
