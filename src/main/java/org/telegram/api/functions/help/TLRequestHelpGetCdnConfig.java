package org.telegram.api.functions.help;

import org.telegram.api.cdn.TLCdnConfig;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;


public class TLRequestHelpGetCdnConfig extends TLMethod<TLCdnConfig> {
    public static final int CLASS_ID = 0x52029342;

    public TLRequestHelpGetCdnConfig() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLCdnConfig deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLCdnConfig))
            return (TLCdnConfig) res;
        throw new IOException("Incorrect response type. Expected " + TLCdnConfig.class.getCanonicalName()  +
                ", got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getCdnConfig#52029342";
    }
}