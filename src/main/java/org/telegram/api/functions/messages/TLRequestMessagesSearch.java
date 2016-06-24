package org.telegram.api.functions.messages;

import org.telegram.api.input.messages.filter.TLAbsMessagesFilter;
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
 * The type TL request messages search.
 */
public class TLRequestMessagesSearch extends TLMethod<TLAbsMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd4569248;

    private static final int FLAG_IMPORTANT_ONLY = 0x00000001; // 0

    private int flags;
    private TLAbsInputPeer peer;
    private String q;
    private TLAbsMessagesFilter filter;
    private int minDate;
    private int maxDate;
    private int offset;
    private int maxId;
    private int limit;

    /**
     * Instantiates a new TL request messages search.
     */
    public TLRequestMessagesSearch() {
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
     * Gets q.
     *
     * @return the q
     */
    public String getQ() {
        return this.q;
    }

    /**
     * Sets q.
     *
     * @param value the value
     */
    public void setQ(String value) {
        this.q = value;
    }

    /**
     * Gets filter.
     *
     * @return the filter
     */
    public TLAbsMessagesFilter getFilter() {
        return this.filter;
    }

    /**
     * Sets filter.
     *
     * @param value the value
     */
    public void setFilter(TLAbsMessagesFilter value) {
        this.filter = value;
    }

    /**
     * Gets min date.
     *
     * @return the min date
     */
    public int getMinDate() {
        return this.minDate;
    }

    /**
     * Sets min date.
     *
     * @param value the value
     */
    public void setMinDate(int value) {
        this.minDate = value;
    }

    /**
     * Gets max date.
     *
     * @return the max date
     */
    public int getMaxDate() {
        return this.maxDate;
    }

    /**
     * Sets max date.
     *
     * @param value the value
     */
    public void setMaxDate(int value) {
        this.maxDate = value;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
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

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeTLObject(this.filter, stream);
        StreamingUtils.writeInt(this.minDate, stream);
        StreamingUtils.writeInt(this.maxDate, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        this.q = StreamingUtils.readTLString(stream);
        this.filter = ((TLAbsMessagesFilter) StreamingUtils.readTLObject(stream, context));
        this.minDate = StreamingUtils.readInt(stream);
        this.maxDate = StreamingUtils.readInt(stream);
        this.offset = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.search#d4569248";
    }
}