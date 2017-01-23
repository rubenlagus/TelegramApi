package org.telegram.api.update;

import org.telegram.api.phone.call.TLAbsPhoneCall;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat user typing.
 */
public class TLUpdatePhoneCall extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xab0f6b1e;

    private TLAbsPhoneCall phoneCall;

    public TLUpdatePhoneCall() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoneCall getPhoneCall() {
        return phoneCall;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.phoneCall, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.phoneCall = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCall.class);
    }

    public String toString() {
        return "updatePhoneCall#ab0f6b1e";
    }
}