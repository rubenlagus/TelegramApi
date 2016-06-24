package org.telegram.api.functions.channels;

import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.input.chat.photo.TLAbsInputChatPhoto;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit photo
 */
public class TLRequestChannelsEditPhoto extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf12e57c9;
    private TLAbsInputChannel channel;
    private TLAbsInputChatPhoto photo;

    /**
     * Instantiates a new TL request channel edit photo
     */
    public TLRequestChannelsEditPhoto() {
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

    public TLAbsInputChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsInputChatPhoto photo) {
        this.photo = photo;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
        this.photo = (TLAbsInputChatPhoto) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "functions.channels.TLRequestChannelsEditPhoto#f12e57c9";
    }
}