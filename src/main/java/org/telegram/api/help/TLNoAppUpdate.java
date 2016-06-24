package org.telegram.api.help;

/**
 * The type TL no app update.
 */
public class TLNoAppUpdate extends TLAbsAppUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc45a6536;

    /**
     * Instantiates a new TL no app update.
     */
    public TLNoAppUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "help.noAppUpdate#c45a6536";
    }
}