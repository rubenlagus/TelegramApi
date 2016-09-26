package org.telegram.api.functions.auth;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TLRequestAuthDropTempAuthKeys
 */
public class TLRequestAuthDropTempAuthKeys extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e48a188;

    private TLLongVector exceptAuthKeys;

    /**
     * Instantiates a new TL request auth send code.
     */
    public TLRequestAuthDropTempAuthKeys() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLLongVector getExceptAuthKeys() {
        return exceptAuthKeys;
    }

    public void setExceptAuthKeys(TLLongVector exceptAuthKeys) {
        this.exceptAuthKeys = exceptAuthKeys;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(exceptAuthKeys, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        exceptAuthKeys = StreamingUtils.readTLLongVector(stream, context);
    }

    public String toString() {
        return "auth.dropTempAuthKeys#8e48a188";
    }
}