package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get messages views.
 */
public class TLRequestMessagesGetMessagesViews extends TLMethod<TLIntVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4c8a55d;

    private TLAbsInputPeer peer;
    private TLIntVector id;
    private boolean increment;

    /**
     * Instantiates a new TL request messages get messages views.
     */
    public TLRequestMessagesGetMessagesViews() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLIntVector)) {
            return (TLIntVector) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLIntVector.class.getName() + ", got: " + res.getClass().getName());
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

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
        StreamingUtils.writeTLBool(this.increment, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.id = StreamingUtils.readTLIntVector(stream, context);
        this.increment = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "messages.getMessagesViews#c4c8a55d";
    }
}