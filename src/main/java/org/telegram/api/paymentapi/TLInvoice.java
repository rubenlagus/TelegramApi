package org.telegram.api.paymentapi;

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
public class TLInvoice extends TLObject {
    public static final int CLASS_ID = 0xc30aa358;

    private static final int FLAG_TEST                         = 0x00000001; // 0
    private static final int FLAG_NAME_REQUESTED               = 0x00000002; // 1
    private static final int FLAG_PHONE_REQUESTED              = 0x00000004; // 2
    private static final int FLAG_EMAIL_REQUESTED              = 0x00000008; // 3
    private static final int FLAG_SHIPPING_ADDRESS_REQUESTED   = 0x00000010; // 4
    private static final int FLAG_FLEXIBLE                     = 0x00000020; // 5

    private int flags;
    private String currency;
    private TLVector<TLLabeledPrice> prices;

    public TLInvoice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getCurrency() {
        return currency;
    }

    public TLVector<TLLabeledPrice> getPrices() {
        return prices;
    }

    public boolean isTest() {
        return (flags & FLAG_TEST) != 0;
    }

    public boolean isNameRequested() {
        return (flags & FLAG_NAME_REQUESTED) != 0;
    }

    public boolean isPhoneRequested() {
        return (flags & FLAG_PHONE_REQUESTED) != 0;
    }

    public boolean isEmailRequested() {
        return (flags & FLAG_EMAIL_REQUESTED) != 0;
    }

    public boolean isShippingAddressRequeted() {
        return (flags & FLAG_SHIPPING_ADDRESS_REQUESTED) != 0;
    }

    public boolean isFlexible() {
        return (flags & FLAG_FLEXIBLE) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeTLVector(prices, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        currency = StreamingUtils.readTLString(stream);
        prices = StreamingUtils.readTLVector(stream, context, TLLabeledPrice.class);
    }

    @Override
    public String toString() {
        return "invoice#c30aa358";
    }
}
