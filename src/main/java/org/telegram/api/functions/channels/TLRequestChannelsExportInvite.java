package org.telegram.api.functions.channels;

import org.telegram.api.chat.invite.TLAbsChatInvite;
import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel export invite
 */
public class TLRequestChannelsExportInvite extends TLMethod<TLAbsChatInvite> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc7560885;

    private TLAbsInputChannel channel;

    /**
     * Instantiates a new TL request channel export invite
     */
    public TLRequestChannelsExportInvite() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChatInvite deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsChatInvite)) {
            return (TLAbsChatInvite) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsChatInvite.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "functions.channels.TLRequestChannelsExportInvite#c7560885";
    }
}