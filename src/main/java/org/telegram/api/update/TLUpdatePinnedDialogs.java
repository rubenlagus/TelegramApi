package org.telegram.api.update;

import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLUpdatePinnedDialogs extends TLAbsUpdate {
    public static final int CLASS_ID = 0xd8caf68d;

    private static final int FLAG_ORDER           = 0x00000001; // 0

    private int flags;
    private TLVector<TLAbsPeer> order;

    public TLUpdatePinnedDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPeer> getOrder() {
        return order;
    }

    public boolean hasOrder() {
        return (flags & FLAG_ORDER) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_ORDER) != 0) {
            StreamingUtils.writeTLVector(order, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_ORDER) != 0) {
            this.order = StreamingUtils.readTLVector(stream, context, TLAbsPeer.class);
        }
    }

    public String toString() {
        return "updatePinnedDialogs#d8caf68d";
    }
}