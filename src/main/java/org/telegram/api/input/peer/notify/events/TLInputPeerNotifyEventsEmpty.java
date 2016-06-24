package org.telegram.api.input.peer.notify.events;

/**
 * The type TL input peer notify events empty.
 */
public class TLInputPeerNotifyEventsEmpty extends TLAbsInputPeerNotifyEvents {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf03064d8;

    /**
     * Instantiates a new TL input peer notify events empty.
     */
    public TLInputPeerNotifyEventsEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPeerNotifyEventsEmpty#f03064d8";
    }
}