package org.telegram.api.input.user;

/**
 * The type TL input user self.
 */
public class TLInputUserSelf extends TLAbsInputUser {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf7c1b13f;

    /**
     * Instantiates a new TL input user self.
     */
    public TLInputUserSelf() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputUserSelf#f7c1b13f";
    }
}
