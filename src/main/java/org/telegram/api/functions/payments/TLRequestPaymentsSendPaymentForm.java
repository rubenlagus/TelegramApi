package org.telegram.api.functions.payments;

import org.telegram.api.input.paymentapi.paymentcredentials.TLAbsInputPaymentCredentials;
import org.telegram.api.paymentapi.payments.result.TLAbsPaymentsPaymentResult;
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
public class TLRequestPaymentsSendPaymentForm extends TLMethod<TLAbsPaymentsPaymentResult> {
    public static final int CLASS_ID = 0x2b8879b3;

    private static final int FLAG_INFO             = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTION  = 0x00000002; // 1

    private int flags;
    private int msgId;
    private String requestedInfo;
    private String shippingOptionId;
    private TLAbsInputPaymentCredentials credentials;

    public TLRequestPaymentsSendPaymentForm() {
        super();
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getRequestedInfo() {
        return requestedInfo;
    }

    public void setRequestedInfo(String requestedInfo) {
        if (requestedInfo == null) {
            flags &= ~FLAG_INFO;
        } else {
            flags |= FLAG_INFO;
        }
        this.requestedInfo = requestedInfo;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public void setShippingOptionId(String shippingOptionId) {
        if (shippingOptionId == null) {
            flags &= ~FLAG_SHIPPING_OPTION;
        } else {
            flags |= FLAG_SHIPPING_OPTION;
        }
        this.shippingOptionId = shippingOptionId;
    }

    public TLAbsInputPaymentCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(TLAbsInputPaymentCredentials credentials) {
        this.credentials = credentials;
    }

    public boolean hasShippingOptions() {
        return (flags & FLAG_SHIPPING_OPTION) != 0;
    }

    public boolean hasInfo() {
        return (flags & FLAG_INFO) != 0;
    }

    @Override
    public TLAbsPaymentsPaymentResult deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsPaymentsPaymentResult)) {
            return (TLAbsPaymentsPaymentResult) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsPaymentsPaymentResult.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(msgId, stream);
        if (hasInfo()) {
            StreamingUtils.writeTLString(requestedInfo, stream);
        }
        if (hasShippingOptions()) {
            StreamingUtils.writeTLString(shippingOptionId, stream);
        }
        StreamingUtils.writeTLObject(credentials, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        msgId = StreamingUtils.readInt(stream);
        if (hasInfo()) {
            requestedInfo = StreamingUtils.readTLString(stream);
        }
        if (hasShippingOptions()) {
            shippingOptionId = StreamingUtils.readTLString(stream);
        }
        credentials = StreamingUtils.readTLObject(stream, context, TLAbsInputPaymentCredentials.class);
    }

    @Override
    public String toString() {
        return "payments.sendPaymentForm#2b8879b3";
    }
}
