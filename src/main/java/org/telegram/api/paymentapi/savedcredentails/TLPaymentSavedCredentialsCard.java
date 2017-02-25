package org.telegram.api.paymentapi.savedcredentails;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentSavedCredentialsCard extends TLAbsPaymentSavedCredentials {
    public static final int CLASS_ID = 0xcdc27a1f;

    private String id;
    private String title;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "paymentSavedCredentialsCard#cdc27a1f";
    }
}
