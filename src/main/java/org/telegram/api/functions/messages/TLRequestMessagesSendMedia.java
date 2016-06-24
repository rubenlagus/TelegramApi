package org.telegram.api.functions.messages;

import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.keyboard.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages send media.
 */
public class TLRequestMessagesSendMedia extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc8f16791;

    private static final int FLAG_REPLY            = 0x00000001; // 0
    private static final int FLAG_UNUSED1          = 0x00000002; // 1
    private static final int FLAG_REPLYMARKUP      = 0x00000004; // 2
    private static final int FLAG_UNUSED3          = 0x00000008; // 3
    private static final int FLAG_BROADCAST        = 0x00000010; // 4
    private static final int FLAG_SILENT           = 0x00000020; // 5
    private static final int FLAG_BACKGROUND       = 0x00000040; // 6

    private int flags;
    private TLAbsInputPeer peer;
    private TLAbsInputMedia media;
    private long randomId;
    private int replyToMsgId;
    private TLAbsReplyMarkup replyMarkup;

    /**
     * Instantiates a new TL request messages send media.
     */
    public TLRequestMessagesSendMedia() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUpdates))
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
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
     * Gets media.
     *
     * @return the media
     */
    public TLAbsInputMedia getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param value the value
     */
    public void setMedia(TLAbsInputMedia value) {
        this.media = value;
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public long getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param value the value
     */
    public void setRandomId(long value) {
        this.randomId = value;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets reply to msg id.
     *
     * @return the reply to msg id
     */
    public int getReplyToMsgId() {
        return this.replyToMsgId;
    }

    /**
     * Sets reply to msg id.
     *
     * @param replyToMsgId the reply to msg id
     */
    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        if ((this.flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeTLObject(this.media, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        if ((this.flags & FLAG_REPLYMARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        if ((this.flags & FLAG_REPLY) != 0) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.media = ((TLAbsInputMedia) StreamingUtils.readTLObject(stream, context));
        this.randomId = StreamingUtils.readLong(stream);
        if ((this.flags & FLAG_REPLYMARKUP) != 0) {
            this.replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
    }

    public String toString() {
        return "messages.sendMedia#c8f16791";
    }
}