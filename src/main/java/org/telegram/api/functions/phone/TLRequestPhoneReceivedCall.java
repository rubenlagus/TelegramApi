package org.telegram.api.functions.phone;

import org.telegram.api.input.phonecall.TLInputPhoneCall;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneReceivedCall extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x17d54f61;

    private TLInputPhoneCall peer;

    public TLRequestPhoneReceivedCall() {
        super();
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if (res instanceof TLBool) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
    }

    @Override
    public String toString() {
        return "phone.receivedCall#17d54f61";
    }
}
