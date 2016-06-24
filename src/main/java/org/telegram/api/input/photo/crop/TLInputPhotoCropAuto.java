package org.telegram.api.input.photo.crop;

/**
 * The type TL input photo crop auto.
 */
public class TLInputPhotoCropAuto extends TLAbsInputPhotoCrop {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xade6b004;

    /**
     * Instantiates a new TL input photo crop auto.
     */
    public TLInputPhotoCropAuto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPhotoCropAuto#ade6b004";
    }
}