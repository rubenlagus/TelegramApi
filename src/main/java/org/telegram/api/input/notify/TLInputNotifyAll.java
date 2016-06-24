package org.telegram.api.input.notify;

/**
 * The type TL input notify all.
 */
public class TLInputNotifyAll extends TLAbsInputNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa429b886;

    /**
     * Instantiates a new TL input notify all.
     */
    public TLInputNotifyAll() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputNotifyAll#a429b886";
    }
}