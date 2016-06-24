package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.TLContactsLink;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts delete contact.
 */
public class TLRequestContactsDeleteContact extends TLMethod<TLContactsLink> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e953744;

    private TLAbsInputUser id;

    /**
     * Instantiates a new TL request contacts delete contact.
     */
    public TLRequestContactsDeleteContact() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLContactsLink deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLContactsLink))
            return (TLContactsLink) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLLink, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLAbsInputUser getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLAbsInputUser value) {
        this.id = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "contacts.deleteContact#8e953744";
    }
}
