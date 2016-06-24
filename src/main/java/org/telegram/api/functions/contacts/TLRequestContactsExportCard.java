package org.telegram.api.functions.contacts;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request contacts export card.
 */
public class TLRequestContactsExportCard extends TLMethod<TLIntVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84e53737;

    /**
     * Instantiates a new TL request contacts export card.
     */
    public TLRequestContactsExportCard() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "contacts.exportCard#84e53737";
    }
}