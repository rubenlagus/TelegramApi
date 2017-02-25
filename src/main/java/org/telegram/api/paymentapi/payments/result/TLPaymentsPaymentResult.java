package org.telegram.api.paymentapi.payments.result;

import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsPaymentResult extends TLAbsPaymentsPaymentResult {
    public static final int CLASS_ID = 0x4e5f810d;

    private TLAbsUpdates updates;

    public TLPaymentsPaymentResult() {
        super();
    }

    public TLAbsUpdates getUpdates() {
        return updates;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(updates, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        updates = StreamingUtils.readTLObject(stream, context, TLAbsUpdates.class);
    }

    @Override
    public String toString() {
        return "payments.paymentResult#4e5f810d";
    }
}
