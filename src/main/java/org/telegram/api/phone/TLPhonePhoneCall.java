package org.telegram.api.phone;

import org.telegram.api.phone.call.TLAbsPhoneCall;
import org.telegram.api.user.TLAbsUser;
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
public class TLPhonePhoneCall extends TLObject {
    public static final int CLASS_ID = 0xec82e140;

    private TLAbsPhoneCall phoneCall;
    private TLVector<TLAbsUser> users;

    public TLPhonePhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoneCall getPhoneCall() {
        return phoneCall;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(phoneCall, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        phoneCall = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCall.class);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "phone.phoneCall#ec82e140";
    }
}
