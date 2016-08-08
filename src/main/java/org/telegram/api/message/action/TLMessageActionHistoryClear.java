package org.telegram.api.message.action;

/**
 * The type TL message action history clear.
 */
public class TLMessageActionHistoryClear extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fbab604;

    /**
     * Instantiates a new TL message action pin message.
     */
    public TLMessageActionHistoryClear() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionHistoryClear#9fbab604";
    }
}