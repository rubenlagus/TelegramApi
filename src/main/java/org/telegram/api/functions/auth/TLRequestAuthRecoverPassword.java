package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth recover password.
 */
public class TLRequestAuthRecoverPassword extends TLMethod<TLAuthorization> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4ea56e92;

    private String code;

    /**
     * Instantiates a new TL request auth recover password.
     */
    public TLRequestAuthRecoverPassword() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public TLAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAuthorization))
            return (TLAuthorization) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.code, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.code = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "auth.recoveryPassword#4ea56e92";
    }
}