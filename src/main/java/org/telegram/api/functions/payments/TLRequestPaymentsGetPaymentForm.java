package org.telegram.api.functions.payments;

import org.telegram.api.paymentapi.payments.TLPaymentsPaymentForm;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPaymentsGetPaymentForm extends TLMethod<TLPaymentsPaymentForm> {
    public static final int CLASS_ID = 0x99f09745;

    private int msgId;

    public TLRequestPaymentsGetPaymentForm() {
        super();
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Override
    public TLPaymentsPaymentForm deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLPaymentsPaymentForm)) {
            return (TLPaymentsPaymentForm) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsPaymentForm.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(msgId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        msgId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "payments.getPaymentForm#99f09745";
    }
}
