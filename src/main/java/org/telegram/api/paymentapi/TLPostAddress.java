package org.telegram.api.paymentapi;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPostAddress extends TLObject {
    public static final int CLASS_ID = 0x1e8caaeb;

    private String streetLine1;
    private String streetLine2;
    private String city;
    private String state;
    private String countryIso2;
    private String postCode;

    public TLPostAddress() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(streetLine1, stream);
        StreamingUtils.writeTLString(streetLine2, stream);
        StreamingUtils.writeTLString(city, stream);
        StreamingUtils.writeTLString(state, stream);
        StreamingUtils.writeTLString(countryIso2, stream);
        StreamingUtils.writeTLString(postCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        streetLine1 = StreamingUtils.readTLString(stream);
        streetLine2 = StreamingUtils.readTLString(stream);
        city = StreamingUtils.readTLString(stream);
        state = StreamingUtils.readTLString(stream);
        countryIso2 = StreamingUtils.readTLString(stream);
        postCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "postAddress#1e8caaeb";
    }
}
