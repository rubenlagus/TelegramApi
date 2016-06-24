package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.TLAffectedMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages read history.
 */
public class TLRequestMessagesReadHistory extends TLMethod<TLAffectedMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe306d3a;

    private TLAbsInputPeer peer;
    private int maxId;

    /**
     * Instantiates a new TL request messages read history.
     */
    public TLRequestMessagesReadHistory() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAffectedMessages)) {
            return (TLAffectedMessages) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAffectedMessages.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public int getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param value the value
     */
    public void setMaxId(int value) {
        this.maxId = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        this.maxId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.readHistory#e306d3a";
    }
}