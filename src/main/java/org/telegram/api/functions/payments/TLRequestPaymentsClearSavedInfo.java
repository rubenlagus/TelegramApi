package org.telegram.api.functions.payments;

import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPaymentsClearSavedInfo extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0xd83d70c1;

    private static final int FLAG_CREDENTIALS       = 0x00000001; // 0
    private static final int FLAG_INFO              = 0x00000002; // 1

    private int flags;

    public TLRequestPaymentsClearSavedInfo() {
        super();
    }

    public void clearCredentials(boolean clearCredentials) {
        if (clearCredentials) {
            flags |= FLAG_CREDENTIALS;
        } else {
            flags &= ~FLAG_CREDENTIALS;
        }
    }

    public void clearInfo(boolean clearInfo) {
        if (clearInfo) {
            flags |= FLAG_INFO;
        } else {
            flags &= ~FLAG_INFO;
        }
    }

    public boolean isClearInfo() {
        return (flags & FLAG_INFO) != 0;
    }

    public boolean isClearCredentails() {
        return (flags & FLAG_CREDENTIALS) != 0;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "payments.clearSavedInfo#d83d70c1";
    }
}
