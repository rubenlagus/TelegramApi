package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.TLAbsMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get history.
 */
public class TLRequestMessagesGetHistory extends TLMethod<TLAbsMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xafa92846;

    private TLAbsInputPeer peer;
    private int offsetId;
    private int offsetDate;
    private int addOffset;
    private int limit;
    private int maxId;
    private int minId;

    /**
     * Instantiates a new TL request messages get history.
     */
    public TLRequestMessagesGetHistory() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsMessages)) {
            return (TLAbsMessages) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsMessages.class.getName() + ", got: " + res.getClass().getCanonicalName());
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

    public int getOffsetDate() {
        return offsetDate;
    }

    public void setOffsetDate(int offsetDate) {
        this.offsetDate = offsetDate;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
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

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Sets limit.
     *
     * @param value the value
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getAddOffset() {
        return addOffset;
    }

    public void setAddOffset(int addOffset) {
        this.addOffset = addOffset;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeInt(this.offsetDate, stream);
        StreamingUtils.writeInt(this.addOffset, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.minId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        this.offsetId = StreamingUtils.readInt(stream);
        this.offsetDate = StreamingUtils.readInt(stream);
        this.addOffset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.minId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.getHistory#afa92846";
    }
}