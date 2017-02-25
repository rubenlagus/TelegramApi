package org.telegram.api.paymentapi.payments.result;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsPaymentVerificationNeeded extends TLAbsPaymentsPaymentResult {
    public static final int CLASS_ID = 0x6b56b921;

    private String url;

    public TLPaymentsPaymentVerificationNeeded() {
        super();
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "payments.paymentVerficationNeeded#6b56b921";
    }
}
