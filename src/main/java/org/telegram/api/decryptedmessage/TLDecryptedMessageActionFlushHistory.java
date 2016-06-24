package org.telegram.api.decryptedmessage;

/**
 * The type TL decrypted message action flush history.
 */
public class TLDecryptedMessageActionFlushHistory extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6719e45c;

    /**
     * Instantiates a new TL decrypted message action flush history.
     */
    public TLDecryptedMessageActionFlushHistory() {
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionFlushHistory#6719e45c";
    }
}