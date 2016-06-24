package org.telegram.api.input.media;

/**
 * The type TL input media empty.
 */
public class TLInputMediaEmpty extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9664f57f;

    /**
     * Instantiates a new TL input media empty.
     */
    public TLInputMediaEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMediaEmpty#9664f57f";
    }
}