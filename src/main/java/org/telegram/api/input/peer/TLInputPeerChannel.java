package org.telegram.api.input.peer;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input peer user.
 */
public class TLInputPeerChannel extends TLAbsInputPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x20adaef8;

    private int channelId;
    private long accessHash;

    /**
     * Instantiates a new TL input peer foreign.
     */
    public TLInputPeerChannel() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets channel id.
     *
     * @return the channel id
     */
    public int getChannelId() {
        return this.channelId;
    }

    /**
     * Sets channel id.
     *
     * @param channelId the channel id
     */
    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "inputPeerChannel#20adaef8";
    }
}