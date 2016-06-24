package org.telegram.api.functions.messages;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages report spam
 */
public class TLRequestMessagesHideReportSpam extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa8f1709b;

    private TLAbsInputUser peer;

    /**
     * Instantiates a new TL request messages report spam
     */
    public TLRequestMessagesHideReportSpam() {
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
        return "messages.hideReportSpam#a8f1709b";
    }
}