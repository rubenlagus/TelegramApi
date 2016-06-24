package org.telegram.api.message.action;

/**
 * The type TL message action chat delete photo.
 */
public class TLMessageActionChatDeletePhoto extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95e3fbef;

    /**
     * Instantiates a new TL message action chat delete photo.
     */
    public TLMessageActionChatDeletePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionChatDeletePhoto#95e3fbef";
    }
}