package org.telegram.api.document;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent an empty document
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLDocumentEmpty extends TLAbsDocument {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x36f8c871;

    /**
     * Instantiates a new TL document empty.
     */
    public TLDocumentEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "documentEmpty#36f8c871";
    }
}