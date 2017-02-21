package org.telegram.api.functions.phone;

import org.telegram.api.input.phonecall.TLInputPhoneCall;
import org.telegram.api.phone.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneDiscardCall extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x5dfbcddc;

    private TLInputPhoneCall peer;
    private int duration;
    private TLAbsPhoneCallDiscardReason reason;
    private long connectionId;

    public TLRequestPhoneDiscardCall() {
        super();
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


    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public void setReason(TLAbsPhoneCallDiscardReason reason) {
        this.reason = reason;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(duration, stream);
        StreamingUtils.writeTLObject(reason, stream);
        StreamingUtils.writeLong(connectionId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        duration = StreamingUtils.readInt(stream);
        reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        connectionId = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "phone.discardCall#5dfbcddc";
    }
}
