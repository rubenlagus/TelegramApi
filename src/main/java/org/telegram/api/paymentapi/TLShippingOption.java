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
public class TLShippingOption extends TLObject {
    public static final int CLASS_ID = 0xb6213cdf;

    private String id;
    private String title;
    private TLVector<TLLabeledPrice> prices;

    public TLShippingOption() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TLVector<TLLabeledPrice> getPrices() {
        return prices;
    }

    public void setPrices(TLVector<TLLabeledPrice> prices) {
        this.prices = prices;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(title, stream);
        StreamingUtils.writeTLVector(prices, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        title = StreamingUtils.readTLString(stream);
        prices = StreamingUtils.readTLVector(stream, context, TLLabeledPrice.class);
    }

    @Override
    public String toString() {
        return "shippingOption#b6213cdf";
    }
}
