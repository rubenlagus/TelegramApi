package org.telegram.api.input.notify;

/**
 * The type TL input notify users.
 */
public class TLInputNotifyUsers extends TLAbsInputNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x193b4417;

    /**
     * Instantiates a new TL input notify users.
     */
    public TLInputNotifyUsers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputNotifyUsers#193b4417";
    }
}