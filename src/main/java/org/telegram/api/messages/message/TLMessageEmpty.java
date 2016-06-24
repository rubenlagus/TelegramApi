package org.telegram.api.messages.message;

/**
 * The type TL message empty.
 */
public class TLMessageEmpty extends TLAbsMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3f4e0648;

    /**
     * Instantiates a new TL message empty.
     */
    public TLMessageEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "messages.messageEmpty#3f4e0648";
    }
}