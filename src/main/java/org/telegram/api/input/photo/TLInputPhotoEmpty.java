package org.telegram.api.input.photo;

/**
 * The type TL input photo empty.
 */
public class TLInputPhotoEmpty extends TLAbsInputPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1cd7bf0d;

    /**
     * Instantiates a new TL input photo empty.
     */
    public TLInputPhotoEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPhotoEmpty#1cd7bf0d";
    }
}