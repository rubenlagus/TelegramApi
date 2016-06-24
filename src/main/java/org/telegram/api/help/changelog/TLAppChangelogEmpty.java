package org.telegram.api.help.changelog;

/**
 * The type TL app changelog empty
 */
public class TLAppChangelogEmpty extends TLAbsAppChangelog {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaf7e0394;

    /**
     * Instantiates a new TL app changelog empty.
     */
    public TLAppChangelogEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }


    public String toString() {
        return "help.appChangelogEmpty#af7e0394";
    }
}