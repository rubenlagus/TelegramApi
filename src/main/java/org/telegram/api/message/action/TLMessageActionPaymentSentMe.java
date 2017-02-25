package org.telegram.api.message.action;

import org.telegram.api.paymentapi.TLPaymentCharge;
import org.telegram.api.paymentapi.TLPaymentRequestedInfo;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class TLMessageActionPaymentSentMe extends TLAbsMessageAction {
    public static final int CLASS_ID = 0x8f31b327;

    private static final int FLAG_INFO                     = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTION_ID       = 0x00000002; // 1

    private int flags;
    private String currency;
    private long totalAmount;
    private TLBytes payload;
    private TLPaymentRequestedInfo info;
    private String shippingOptionId;
    private TLPaymentCharge charge;


    public TLMessageActionPaymentSentMe() {
        super();
    }

    public String getCurrency() {
        return currency;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public TLBytes getPayload() {
        return payload;
    }

    public TLPaymentRequestedInfo getInfo() {
        return info;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public TLPaymentCharge getCharge() {
        return charge;
    }

    public boolean hasInfo() {
        return (flags & FLAG_INFO) != 0;
    }

    public boolean hasShippingOption() {
        return (flags & FLAG_SHIPPING_OPTION_ID) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeLong(totalAmount, stream);
        StreamingUtils.writeTLBytes(payload, stream);
        if ((flags & FLAG_INFO) != 0) {
            StreamingUtils.writeTLObject(info, stream);
        }
        if ((flags & FLAG_SHIPPING_OPTION_ID) != 0) {
            StreamingUtils.writeTLString(shippingOptionId, stream);
        }
        StreamingUtils.writeTLObject(charge, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        currency = StreamingUtils.readTLString(stream);
        totalAmount = StreamingUtils.readLong(stream);
        payload = StreamingUtils.readTLBytes(stream, context);
        if ((flags & FLAG_INFO) != 0) {
            info = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
        if ((flags & FLAG_SHIPPING_OPTION_ID) != 0) {
            shippingOptionId = StreamingUtils.readTLString(stream);
        }
        charge = StreamingUtils.readTLObject(stream, context, TLPaymentCharge.class);
    }

    @Override
    public String toString() {
        return "messageActionPaymentSentMe#8f31b327";
    }
}