package org.telegram.api.decryptedmessage;

/**
 * The type TL decrypted message action noop.
 */
public class TLDecryptedMessageActionNoop extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa82fdd63;

    /**
     * Instantiates a new TL decrypted message action noop.
     */
    public TLDecryptedMessageActionNoop() {
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionNoop#a82fdd63";
    }
}