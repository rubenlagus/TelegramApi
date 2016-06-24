package org.telegram.api.functions.messages;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.peer.TLPeerSettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages report spam
 */
public class TLRequestMessagesGetPeerSettings extends TLMethod<TLPeerSettings> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3672e09c;

    private TLAbsInputUser peer;

    /**
     * Instantiates a new TL request messages report spam
     */
    public TLRequestMessagesGetPeerSettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLPeerSettings deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLPeerSettings)) {
            return (TLPeerSettings) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPeerSettings.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputUser getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputUser peer) {
        this.peer = peer;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputUser) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "messages.getPeerSettings#3672e09c";
    }
}