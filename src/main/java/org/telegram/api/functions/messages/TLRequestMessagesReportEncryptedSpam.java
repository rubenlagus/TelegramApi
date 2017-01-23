package org.telegram.api.functions.messages;

import org.telegram.api.input.encrypted.TLInputEncryptedChat;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesReportEncryptedSpam extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4b0c8c0f;

    private TLInputEncryptedChat peer;

    public TLRequestMessagesReportEncryptedSpam() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLInputEncryptedChat getPeer() {
        return peer;
    }

    public void setPeer(TLInputEncryptedChat peer) {
        this.peer = peer;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
    }

    public String toString() {
        return "messages.reportEncryptedSpam#4b0c8c0f";
    }
}