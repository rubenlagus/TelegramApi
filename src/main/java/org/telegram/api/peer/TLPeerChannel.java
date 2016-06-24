package org.telegram.api.peer;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL peer channel.
 */
public class TLPeerChannel extends TLAbsPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbddde532;

    /**
     * Instantiates a new TL peer channel.
     */
    public TLPeerChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerChannel#bddde532";
    }
}