package org.telegram.api.paymentapi.payments;

import org.telegram.api.paymentapi.TLPaymentRequestedInfo;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsSavedInfo extends TLObject {
    public static final int CLASS_ID = 0xfb8fe43c;

    private static final int FLAG_SAVED_INFO                  = 0x00000001; // 0
    private static final int FLAG_HAS_SAVED_CREDENTIALS       = 0x00000002; // 1

    public int flags;
    public TLPaymentRequestedInfo savedInfo;

    public TLPaymentsSavedInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLPaymentRequestedInfo getSavedInfo() {
        return savedInfo;
    }

    public boolean hasSavedInfo() {
        return (flags & FLAG_SAVED_INFO) != 0;
    }

    public boolean hasSavedCredentials() {
        return (flags & FLAG_HAS_SAVED_CREDENTIALS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if (hasSavedInfo()) {
            StreamingUtils.writeTLObject(savedInfo, stream);
        }

    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if (hasSavedInfo()) {
            savedInfo = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
    }

    @Override
    public String toString() {
        return "payments.savedInfo#fb8fe43c";
    }
}
