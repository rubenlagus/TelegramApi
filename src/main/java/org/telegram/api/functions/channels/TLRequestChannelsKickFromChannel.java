package org.telegram.api.functions.channels;

import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel invite to channel
 */
public class TLRequestChannelsKickFromChannel extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa672de14;

    private TLAbsInputChannel channel;
    private TLAbsInputUser userId;
    private boolean kicked;

    /**
     * Instantiates a new TL request channel invite to channel
     */
    public TLRequestChannelsKickFromChannel() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public boolean isKicked() {
        return kicked;
    }

    public void setKicked(boolean kicked) {
        this.kicked = kicked;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLBool(this.kicked, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
        this.userId = (TLAbsInputUser) StreamingUtils.readTLObject(stream, context);
        this.kicked = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "functions.channels.TLRequestChannelsKickFromChannel#a672de14";
    }
}