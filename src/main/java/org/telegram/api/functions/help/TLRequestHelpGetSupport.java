package org.telegram.api.functions.help;

import org.telegram.api.help.TLSupport;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get support.
 */
public class TLRequestHelpGetSupport extends TLMethod<TLSupport> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cdf08cd;

    /**
     * Instantiates a new TL request help get support.
     */
    public TLRequestHelpGetSupport() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLSupport deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLSupport))
            return (TLSupport) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLSupport, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getSupport#9cdf08cd";
    }
}