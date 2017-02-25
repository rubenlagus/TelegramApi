package org.telegram.api.paymentapi.payments;

import org.telegram.api.paymentapi.TLShippingOption;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsValidatedRequestedInfo extends TLObject {
    public static final int CLASS_ID = 0xd1451883;

    private static final int FLAG_ID                     = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTIONS       = 0x00000002; // 1

    private int flags;
    private String id;
    private TLVector<TLShippingOption> shippingOptions;

    public TLPaymentsValidatedRequestedInfo() {
        super();
    }

    public String getId() {
        return id;
    }

    public TLVector<TLShippingOption> getShippingOptions() {
        return shippingOptions;
    }

    public boolean hasId() {
        return (flags & FLAG_ID) != 0;
    }

    public boolean hasShippingOptions() {
        return (flags & FLAG_SHIPPING_OPTIONS) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if (hasId()) {
            StreamingUtils.writeTLString(id, stream);
        }
        if (hasShippingOptions()) {
            StreamingUtils.writeTLVector(shippingOptions, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if (hasId()) {
            id = StreamingUtils.readTLString(stream);
        }
        if (hasShippingOptions()) {
            shippingOptions = StreamingUtils.readTLVector(stream, context, TLShippingOption.class);
        }
    }

    @Override
    public String toString() {
        return "payments.validatedRequestedInfo#d1451883";
    }
}
