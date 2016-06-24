package org.telegram.api.input.peer;

/**
 * The type TL input peer self.
 */
public class TLInputPeerSelf extends TLAbsInputPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7da07ec9;

    /**
     * Instantiates a new TL input peer self.
     */
    public TLInputPeerSelf() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPeerSelf#7da07ec9";
    }
}