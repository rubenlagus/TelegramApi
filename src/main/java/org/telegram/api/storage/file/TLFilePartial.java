package org.telegram.api.storage.file;

/**
 * The type TL file partial.
 */
public class TLFilePartial extends TLAbsFileType {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40bc6f52;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "storage.filePartial#40bc6f52";
    }
}