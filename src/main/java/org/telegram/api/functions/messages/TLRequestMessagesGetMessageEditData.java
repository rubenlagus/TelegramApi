package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.TLMessagesEditData;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit admin
 */
public class TLRequestMessagesGetMessageEditData extends TLMethod<TLMessagesEditData> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfda68d36;

    private TLAbsInputPeer peer;
    private int id;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestMessagesGetMessageEditData() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TLMessagesEditData deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesEditData)) {
            return (TLMessagesEditData) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesEditData.class.getName() +", got: " + res.getClass().getName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getMessageEditData#fda68d36";
    }
}