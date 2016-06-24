package org.telegram.api.update;

import org.telegram.api.notify.peer.TLAbsNotifyPeer;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update notify settings.
 */
public class TLUpdateNotifySettings extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbec268ef;

    private TLAbsNotifyPeer peer;
    private TLAbsPeerNotifySettings notifySettings;

    /**
     * Instantiates a new TL update notify settings.
     */
    public TLUpdateNotifySettings() {
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
    public TLAbsNotifyPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param peer the peer
     */
    public void setPeer(TLAbsNotifyPeer peer) {
        this.peer = peer;
    }

    /**
     * Gets notify settings.
     *
     * @return the notify settings
     */
    public TLAbsPeerNotifySettings getNotifySettings() {
        return this.notifySettings;
    }

    /**
     * Sets notify settings.
     *
     * @param notifySettings the notify settings
     */
    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsNotifyPeer) StreamingUtils.readTLObject(stream, context);
        this.notifySettings = (TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "updateNotifySettings#bec268ef";
    }
}