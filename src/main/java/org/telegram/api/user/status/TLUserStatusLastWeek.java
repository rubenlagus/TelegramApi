package org.telegram.api.user.status;

/**
 * The type TL user status last week.
 */
public class TLUserStatusLastWeek extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7bf09fc;

    /**
     * Instantiates a new TL user status last week.
     */
    public TLUserStatusLastWeek() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "userStatusLastWeek#7bf09fc";
    }
}