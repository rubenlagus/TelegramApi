/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicate the name of the document.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLDocumentAttributeFilename extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15590068;

    private String fileName; ///< Name of the document

    /**
     * Instantiates a new TL document attribute filename.
     */
    public TLDocumentAttributeFilename() {
        super();
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.fileName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.fileName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeFilename#15590068";
    }
}
