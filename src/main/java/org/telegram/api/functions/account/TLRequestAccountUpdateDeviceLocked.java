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
 * The type TL request account update device locked.
 */
public class TLRequestAccountUpdateDeviceLocked extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x38df3532;

    private int period;

    /**
     * Instantiates a new TL request account update device locked.
     */
    public TLRequestAccountUpdateDeviceLocked() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets period.
     *
     * @return the period
     */
    public int getPeriod() {
        return this.period;
    }

    /**
     * Sets period.
     *
     * @param period the period
     */
    public void setPeriod(int period) {
        this.period = period;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.privacy.TLBool, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.period, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.period = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "account.updateDeviceLocked#38df3532";
    }
}