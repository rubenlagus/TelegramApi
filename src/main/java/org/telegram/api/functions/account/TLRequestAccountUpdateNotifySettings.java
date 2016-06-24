package org.telegram.api.functions.account;

import org.telegram.api.input.notify.TLAbsInputNotifyPeer;
import org.telegram.api.input.peer.notify.TLInputPeerNotifySettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account update notify settings.
 */
public class TLRequestAccountUpdateNotifySettings extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84be5b93;

    private TLAbsInputNotifyPeer peer;
    private TLInputPeerNotifySettings settings;

    /**
     * Instantiates a new TL request account update notify settings.
     */
    public TLRequestAccountUpdateNotifySettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
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

    /**
     * Gets settings.
     *
     * @return the settings
     */
    public TLInputPeerNotifySettings getSettings() {
        return this.settings;
    }

    /**
     * Sets settings.
     *
     * @param value the value
     */
    public void setSettings(TLInputPeerNotifySettings value) {
        this.settings = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputNotifyPeer) StreamingUtils.readTLObject(stream, context));
        this.settings = ((TLInputPeerNotifySettings) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "account.updateNotifySettings#84be5b93";
    }
}