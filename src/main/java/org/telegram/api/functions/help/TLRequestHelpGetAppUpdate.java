package org.telegram.api.functions.help;

import org.telegram.api.help.TLAbsAppUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get app update.
 */
public class TLRequestHelpGetAppUpdate extends TLMethod<TLAbsAppUpdate> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae2de196;

    /**
     * Instantiates a new TL request help get app update.
     */
    public TLRequestHelpGetAppUpdate() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAppUpdate deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsAppUpdate))
            return (TLAbsAppUpdate) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getAppUpdate#ae2de196";
    }
}