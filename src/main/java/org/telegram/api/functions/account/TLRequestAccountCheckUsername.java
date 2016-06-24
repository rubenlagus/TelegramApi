package org.telegram.api.functions.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account check username.
 */
public class TLRequestAccountCheckUsername extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2714d86c;

    private String userName;

    /**
     * Instantiates a new TL request account check username.
     */
    public TLRequestAccountCheckUsername() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.userName, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userName = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "account.checkUsername#2714d86c";
    }
}