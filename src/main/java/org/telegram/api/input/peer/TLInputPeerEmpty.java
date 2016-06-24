package org.telegram.api.input.peer;

/**
 * The type TL input peer empty.
 */
public class TLInputPeerEmpty extends TLAbsInputPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f3b18ea;

    /**
     * Instantiates a new TL input peer empty.
     */
    public TLInputPeerEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPeerEmpty#7f3b18ea";
    }
}