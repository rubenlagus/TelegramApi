package org.telegram.api.functions.help;

import org.telegram.api.help.changelog.TLAbsAppChangelog;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get app changelog.
 */
public class TLRequestHelpGetAppChangelog extends TLMethod<TLAbsAppChangelog> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb921197a;

    /**
     * Instantiates a new TL request help get app changelog.
     */
    public TLRequestHelpGetAppChangelog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAppChangelog deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsAppChangelog)) {
            return (TLAbsAppChangelog) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsAppChangelog.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getAppChangelog#b921197a";
    }
}