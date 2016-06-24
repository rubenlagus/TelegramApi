package org.telegram.api.user.status;

/**
 * The type TL user status recently.
 */
public class TLUserStatusRecently extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe26f42f1;

    /**
     * Instantiates a new TL user status recently.
     */
    public TLUserStatusRecently() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "userStatusRecently#e26f42f1";
    }
}