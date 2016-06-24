package org.telegram.api.updates;

/**
 * The type TL updates too long.
 */
public class TLUpdatesTooLong extends TLAbsUpdates {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe317af7e;

    /**
     * Instantiates a new TL updates too long.
     */
    public TLUpdatesTooLong() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "updatesTooLong#e317af7e";
    }
}