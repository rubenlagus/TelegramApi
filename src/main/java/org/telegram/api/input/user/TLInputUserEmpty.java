package org.telegram.api.input.user;

/**
 * The type TL input user empty.
 */
public class TLInputUserEmpty extends TLAbsInputUser {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb98886cf;

    /**
     * Instantiates a new TL input user empty.
     */
    public TLInputUserEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputUserEmpty#b98886cf";
    }
}