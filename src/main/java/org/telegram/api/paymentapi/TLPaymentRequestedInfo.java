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
public class TLPaymentRequestedInfo extends TLObject {
    public static final int CLASS_ID = 0x909c3f94;

    private static final int FLAG_NAME                   = 0x00000001; // 0
    private static final int FLAG_PHONE                  = 0x00000002; // 1
    private static final int FLAG_EMIAL                  = 0x00000004; // 2
    private static final int FLAG_SHIPPING_ADDRESS       = 0x00000008; // 3

    private int flags;
    private String name;
    private String phone;
    private String email;
    private TLPostAddress shippingAddress;

    public TLPaymentRequestedInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public TLPostAddress getShippingAddress() {
        return shippingAddress;
    }

    public boolean hasName() {
        return (flags & FLAG_NAME) != 0;
    }

    public boolean hasPhone() {
        return (flags & FLAG_PHONE) != 0;
    }

    public boolean hasEmail() {
        return (flags & FLAG_EMIAL) != 0;
    }

    public boolean hasShippingAddress() {
        return (flags & FLAG_SHIPPING_ADDRESS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if (hasName()) {
            StreamingUtils.writeTLString(name, stream);
        }
        if (hasPhone()) {
            StreamingUtils.writeTLString(phone, stream);
        }
        if (hasEmail()) {
            StreamingUtils.writeTLString(email, stream);
        }
        if (hasShippingAddress()) {
            StreamingUtils.writeTLObject(shippingAddress, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if (hasName()) {
            name = StreamingUtils.readTLString(stream);
        }
        if (hasPhone()) {
            phone = StreamingUtils.readTLString(stream);
        }
        if (hasEmail()) {
            email = StreamingUtils.readTLString(stream);
        }
        if (hasShippingAddress()) {
            shippingAddress = StreamingUtils.readTLObject(stream, context, TLPostAddress.class);
        }
    }

    @Override
    public String toString() {
        return "paymentRequestedInfo#909c3f94";
    }
}
