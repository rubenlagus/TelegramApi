package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class TLMessageActionPaymentSent extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40699cd0;

    private String currency;
    private long totalAmount;

    public TLMessageActionPaymentSent() {
        super();
    }

    public String getCurrency() {
        return currency;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeLong(totalAmount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        currency = StreamingUtils.readTLString(stream);
        totalAmount = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "messageActionPaymentSent#40699cd0";
    }
}