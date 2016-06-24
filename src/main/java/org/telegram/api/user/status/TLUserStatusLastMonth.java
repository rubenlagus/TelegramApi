package org.telegram.api.user.status;

/**
 * The type TL user status last month.
 */
public class TLUserStatusLastMonth extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x77ebc742;

    /**
     * Instantiates a new TL user status last month.
     */
    public TLUserStatusLastMonth() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "userStatusLastMonth#77ebc742";
    }
}