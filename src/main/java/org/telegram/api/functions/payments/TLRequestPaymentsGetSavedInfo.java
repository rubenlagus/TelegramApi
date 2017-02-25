package org.telegram.api.functions.payments;

import org.telegram.api.paymentapi.payments.TLPaymentsSavedInfo;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPaymentsGetSavedInfo extends TLMethod<TLPaymentsSavedInfo> {
    public static final int CLASS_ID = 0x227d824b;

    public TLRequestPaymentsGetSavedInfo() {
        super();
    }

    @Override
    public TLPaymentsSavedInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLPaymentsSavedInfo)) {
            return (TLPaymentsSavedInfo) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsSavedInfo.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "payments.getSavedInfo#227d824b";
    }
}
