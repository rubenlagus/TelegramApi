package org.telegram.api.update;

/**
 * The type TL update channel new message
 */
public class TLUpdatePtsChanged extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3354678f;

    /**
     * Instantiates a new TL update channel new message
     */
    public TLUpdatePtsChanged() {
        super();
    }
    
    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "updatePtsChanged#3354678f";
    }
}