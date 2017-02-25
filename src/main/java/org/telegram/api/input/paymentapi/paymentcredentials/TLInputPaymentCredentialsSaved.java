package org.telegram.api.input.paymentapi.paymentcredentials;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLInputPaymentCredentialsSaved extends TLAbsInputPaymentCredentials {
    public static final int CLASS_ID = 0xc10eb2cf;

    private String id;
    private TLBytes tmpPassword;

    public TLInputPaymentCredentialsSaved() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TLBytes getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(TLBytes tmpPassword) {
        this.tmpPassword = tmpPassword;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLBytes(tmpPassword, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        tmpPassword = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputPaymentCredentialsSaved#c10eb2cf";
    }
}
