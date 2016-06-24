package org.telegram.api.storage.file;

/**
 * The type TL file mp 3.
 */
public class TLFileMp3 extends TLAbsFileType {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x528a0677;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "storage.fileMp3#528a0677";
    }
}