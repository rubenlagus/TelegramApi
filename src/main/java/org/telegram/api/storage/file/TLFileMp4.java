package org.telegram.api.storage.file;

/**
 * The type TL file mp 4.
 */
public class TLFileMp4 extends TLAbsFileType {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb3cea0e4;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "storage.fileMp4#b3cea0e4";
    }
}