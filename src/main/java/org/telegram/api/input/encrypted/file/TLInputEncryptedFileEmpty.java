package org.telegram.api.input.encrypted.file;

/**
 * The type TL input encrypted file empty.
 */
public class TLInputEncryptedFileEmpty extends TLAbsInputEncryptedFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1837c364;

    /**
     * Instantiates a new TL input encrypted file empty.
     */
    public TLInputEncryptedFileEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputEncryptedFileEmpty#1837c364";
    }
}