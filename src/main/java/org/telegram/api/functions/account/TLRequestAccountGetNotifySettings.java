package org.telegram.api.functions.account;

import org.telegram.api.input.notify.TLAbsInputNotifyPeer;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account get notify settings.
 */
public class TLRequestAccountGetNotifySettings extends TLMethod<TLAbsPeerNotifySettings> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12b3ad31;

    private TLAbsInputNotifyPeer peer;

    /**
     * Instantiates a new TL request account get notify settings.
     */
    public TLRequestAccountGetNotifySettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeerNotifySettings deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsPeerNotifySettings))
            return (TLAbsPeerNotifySettings) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsInputNotifyPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsInputNotifyPeer value) {
        this.peer = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputNotifyPeer) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "account.getNotifySettings#12b3ad31";
    }
}