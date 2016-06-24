package org.telegram.api.user.status;

/**
 * The type TL user status empty.
 */
public class TLUserStatusEmpty extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9d05049;

    /**
     * Instantiates a new TL user status empty.
     */
    public TLUserStatusEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "userStatusEmpty#9d05049";
    }
}