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
 * The type TL request account update status.
 */
public class TLRequestAccountUpdateStatus extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6628562c;

    private boolean offline;

    /**
     * Instantiates a new TL request account update status.
     */
    public TLRequestAccountUpdateStatus() {
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
     * Gets offline.
     *
     * @return the offline
     */
    public boolean getOffline() {
        return this.offline;
    }

    /**
     * Sets offline.
     *
     * @param value the value
     */
    public void setOffline(boolean value) {
        this.offline = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBool(this.offline, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.offline = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "account.updateStatus#6628562c";
    }
}