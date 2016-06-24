package org.telegram.api.functions.messages;

import org.telegram.api.messages.TLAffectedMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages delete messages.
 */
public class TLRequestMessagesDeleteMessages extends TLMethod<TLAffectedMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa5f18925;

    private TLIntVector id;

    /**
     * Instantiates a new TL request messages delete messages.
     */
    public TLRequestMessagesDeleteMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAffectedMessages))
            return (TLAffectedMessages) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAffectedMessages, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLIntVector getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(TLIntVector id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "messages.deleteMessages#a5f18925";
    }
}