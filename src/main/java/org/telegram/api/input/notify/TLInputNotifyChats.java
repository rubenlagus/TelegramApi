package org.telegram.api.input.notify;

/**
 * The type TL input notify chats.
 */
public class TLInputNotifyChats extends TLAbsInputNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4a95e84e;

    /**
     * Instantiates a new TL input notify chats.
     */
    public TLInputNotifyChats() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputNotifyChats#4a95e84e";
    }
}