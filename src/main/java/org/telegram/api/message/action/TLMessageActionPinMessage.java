package org.telegram.api.message.action;

/**
 * The type TL message action channel create.
 */
public class TLMessageActionPinMessage extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x94bd38ed;

    /**
     * Instantiates a new TL message action pin message.
     */
    public TLMessageActionPinMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionPinMessage#94bd38ed";
    }
}