package org.telegram.api.peer.notify.events;

/**
 * The type TL peer notify events all.
 */
public class TLPeerNotifyEventsAll extends TLAbsPeerNotifyEvents {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6d1ded88;

    /**
     * Instantiates a new TL peer notify events all.
     */
    public TLPeerNotifyEventsAll() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "peerNotifyEventsAll#6d1ded88";
    }
}