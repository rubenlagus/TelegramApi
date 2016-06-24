package org.telegram.api.input.peer.notify.events;

/**
 * The type TL input peer notify events all.
 */
public class TLInputPeerNotifyEventsAll extends TLAbsInputPeerNotifyEvents {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe86a2c74;

    /**
     * Instantiates a new TL input peer notify events all.
     */
    public TLInputPeerNotifyEventsAll() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPeerNotifyEventsAll#e86a2c74";
    }
}