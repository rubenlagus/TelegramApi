package org.telegram.api.functions.phone;

import org.telegram.api.input.user.TLAbsInputUser;
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
public class TLRequestPhoneRequestCall extends TLMethod<TLPhonePhoneCall> {
    public static final int CLASS_ID = 0x5b95b3d4;

    private TLAbsInputUser userId;
    private int randomId;
    private TLBytes gAHash;
    private TLPhoneCallProtocol protocol;

    public TLRequestPhoneRequestCall() {
        super();
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    public TLBytes getgAHash() {
        return gAHash;
    }

    public void setgAHash(TLBytes gAHash) {
        this.gAHash = gAHash;
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
        StreamingUtils.writeTLObject(userId, stream);
        StreamingUtils.writeInt(randomId, stream);
        StreamingUtils.writeTLBytes(gAHash, stream);
        StreamingUtils.writeTLObject(protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        randomId = StreamingUtils.readInt(stream);
        gAHash = StreamingUtils.readTLBytes(stream, context);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phone.requestCall#5b95b3d4";
    }
}
