package org.telegram.api.functions.phone;

import org.telegram.api.input.phonecall.TLInputPhoneCall;
import org.telegram.api.phone.TLPhoneCallProtocol;
import org.telegram.api.phone.TLPhonePhoneCall;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneAcceptCall extends TLMethod<TLPhonePhoneCall> {
    public static final int CLASS_ID = 0x220f0b20;

    private TLInputPhoneCall peer;
    private TLBytes gB;
    private long keyFingerprint;
    private TLPhoneCallProtocol protocol;

    public TLRequestPhoneAcceptCall() {
        super();
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public TLBytes getgB() {
        return gB;
    }

    public void setgB(TLBytes gB) {
        this.gB = gB;
    }

    public long getKeyFingerprint() {
        return keyFingerprint;
    }

    public void setKeyFingerprint(long keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public TLPhonePhoneCall deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if (res instanceof TLPhonePhoneCall) {
            return (TLPhonePhoneCall) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPhonePhoneCall.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLBytes(gB, stream);
        StreamingUtils.writeLong(keyFingerprint, stream);
        StreamingUtils.writeTLObject(protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        gB = StreamingUtils.readTLBytes(stream, context);
        keyFingerprint = StreamingUtils.readLong(stream);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phone.acceptCall#220f0b20";
    }
}
