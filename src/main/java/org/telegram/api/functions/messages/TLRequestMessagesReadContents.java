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
 * The type TL request messages read contents.
 */
public class TLRequestMessagesReadContents extends TLMethod<TLAffectedMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x36a73f77;

    private TLIntVector id;

    /**
     * Instantiates a new TL request messages read contents.
     */
    public TLRequestMessagesReadContents() {
        super();
    }

    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAffectedMessages))
            return (TLAffectedMessages) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.messages.TLAffectedMessages, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.readMessageContents#36a73f77";
    }
}