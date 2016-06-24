package org.telegram.api.user.profile.photo;

/**
 * The type TL user profile photo empty.
 */
public class TLUserProfilePhotoEmpty extends TLAbsUserProfilePhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f11bae1;

    /**
     * Instantiates a new TL user profile photo empty.
     */
    public TLUserProfilePhotoEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "userProfilePhotoEmpty#4f11bae1";
    }
}