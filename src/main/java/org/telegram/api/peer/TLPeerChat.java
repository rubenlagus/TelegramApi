package org.telegram.api.peer;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL peer chat.
 */
public class TLPeerChat extends TLAbsPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbad0e5bb;

    /**
     * Instantiates a new TL peer chat.
     */
    public TLPeerChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "peerChat#bad0e5bb";
    }
}