package org.telegram.api.functions.help;

import org.telegram.api.TLConfig;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get config.
 */
public class TLRequestHelpGetConfig extends TLMethod<TLConfig> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4f9186b;

    /**
     * Instantiates a new TL request help get config.
     */
    public TLRequestHelpGetConfig() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLConfig deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLConfig))
            return (TLConfig) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.TLConfig, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getConfig#c4f9186b";
    }
}