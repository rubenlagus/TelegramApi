package org.telegram.api.storage.file;

/**
 * The type TL file pdf.
 */
public class TLFilePdf extends TLAbsFileType {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae1e508d;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "storage.filePdf#ae1e508d";
    }
}