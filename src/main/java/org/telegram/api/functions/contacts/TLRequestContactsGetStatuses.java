package org.telegram.api.functions.contacts;

import org.telegram.api.contact.TLContactStatus;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request contacts get statuses.
 */
public class TLRequestContactsGetStatuses extends TLMethod<TLVector<TLContactStatus>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4a353ee;

    /**
     * Instantiates a new TL request contacts get statuses.
     */
    public TLRequestContactsGetStatuses() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLContactStatus> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "contacts.getStatuses#c4a353ee";
    }
}