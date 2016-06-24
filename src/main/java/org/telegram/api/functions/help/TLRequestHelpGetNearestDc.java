package org.telegram.api.functions.help;

import org.telegram.api.TLNearestDc;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get nearest dc.
 */
public class TLRequestHelpGetNearestDc extends TLMethod<TLNearestDc> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1fb33026;

    /**
     * Instantiates a new TL request help get nearest dc.
     */
    public TLRequestHelpGetNearestDc() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLNearestDc deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLNearestDc))
            return (TLNearestDc) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.TLNearestDc, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getNearestDc#1fb33026";
    }
}