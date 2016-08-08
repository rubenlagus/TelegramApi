package org.telegram.api.update;

/**
 * The type TL update channel new message
 */
public class TLUpdateReadFeaturedStickers extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x571d2742;

    /**
     * Instantiates a new TL update channel new message
     */
    public TLUpdateReadFeaturedStickers() {
        super();
    }
    
    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "updateReadFeaturedStickers#571d2742";
    }
}