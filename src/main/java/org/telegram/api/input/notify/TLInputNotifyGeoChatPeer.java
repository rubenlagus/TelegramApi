package org.telegram.api.input.notify;

import org.telegram.api.input.TLInputGeoChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input notify geo chat peer.
 */
public class TLInputNotifyGeoChatPeer extends TLAbsInputNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4d8ddec8;
    /**
     * The Peer.
     */
    protected TLInputGeoChat peer;

    /**
     * Instantiates a new TL input notify geo chat peer.
     */
    public TLInputNotifyGeoChatPeer() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLInputGeoChat getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLInputGeoChat value) {
        this.peer = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLInputGeoChat) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "inputNotifyGeoChatPeer#4d8ddec8";
    }
}
