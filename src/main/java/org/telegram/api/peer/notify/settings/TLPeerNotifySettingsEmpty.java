package org.telegram.api.peer.notify.settings;

/**
 * The type TL peer notify settings empty.
 */
public class TLPeerNotifySettingsEmpty extends TLAbsPeerNotifySettings {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x70a68512;

    /**
     * Instantiates a new TL peer notify settings empty.
     */
    public TLPeerNotifySettingsEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "peerNotifySettingsEmpty#70a68512";
    }
}