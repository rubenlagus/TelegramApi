package org.telegram.api.functions.payments;

import org.telegram.api.paymentapi.TLPaymentRequestedInfo;
import org.telegram.api.paymentapi.payments.TLPaymentsValidatedRequestedInfo;
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
public class TLRequestPaymentsValidateRequestedInfo extends TLMethod<TLPaymentsValidatedRequestedInfo> {
    public static final int CLASS_ID = 0x770a8e74;

    private static final int FLAG_SAVE       = 0x00000001; // 0

    private int flags;
    private int msgId;
    private TLPaymentRequestedInfo info;

    public TLRequestPaymentsValidateRequestedInfo() {
        super();
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public TLPaymentRequestedInfo getInfo() {
        return info;
    }

    public void setInfo(TLPaymentRequestedInfo info) {
        this.info = info;
    }

    public void setSave(boolean save) {
        if (save) {
            flags |= FLAG_SAVE;
        } else {
            flags &= ~FLAG_SAVE;
        }
    }

    public boolean isSave() {
        return (flags & FLAG_SAVE) != 0;
    }

    @Override
    public TLPaymentsValidatedRequestedInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLPaymentsValidatedRequestedInfo)) {
            return (TLPaymentsValidatedRequestedInfo) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsValidatedRequestedInfo.class.getName() +
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
        StreamingUtils.writeTLObject(info, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        msgId = StreamingUtils.readInt(stream);
        info = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
    }

    @Override
    public String toString() {
        return "payments.validateRequestedInfo#770a8e74";
    }
}
