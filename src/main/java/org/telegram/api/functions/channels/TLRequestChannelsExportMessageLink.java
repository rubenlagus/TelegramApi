package org.telegram.api.functions.channels;

import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.message.TLExportedMessageLink;
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
public class TLRequestChannelsExportMessageLink extends TLMethod<TLExportedMessageLink> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc846d22d;

    private TLAbsInputChannel channel;
    private int id;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestChannelsExportMessageLink() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLExportedMessageLink deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLExportedMessageLink)) {
            return (TLExportedMessageLink) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLExportedMessageLink.class.getName() +", got: " + res.getClass().getName());
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
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "channels.exportMessageLink#c846d22d";
    }
}