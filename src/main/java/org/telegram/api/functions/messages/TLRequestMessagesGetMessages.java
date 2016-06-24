package org.telegram.api.functions.messages;

import org.telegram.api.messages.TLAbsMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get messages.
 */
public class TLRequestMessagesGetMessages extends TLMethod<TLAbsMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4222fa74;

    private TLIntVector id;

    /**
     * Instantiates a new TL request messages get messages.
     */
    public TLRequestMessagesGetMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsMessages))
            return (TLAbsMessages) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAbsMessages, got: " + res.getClass().getCanonicalName());
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
     * @param value the value
     */
    public void setId(TLIntVector value) {
        this.id = value;
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
        return "messages.getMessages#4222fa74";
    }
}