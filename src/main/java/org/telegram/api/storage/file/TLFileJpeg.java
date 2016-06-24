package org.telegram.api.storage.file;

/**
 * The type TL file jpeg.
 */
public class TLFileJpeg extends TLAbsFileType {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7efe0e;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "storage.fileJpeg#7efe0e";
    }
}