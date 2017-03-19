package org.telegram.api.functions.help;

import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestHelpGetAppChangelog extends TLMethod<TLAbsUpdates> {
    public static final int CLASS_ID = 0x9010ef6f;

    private String prevAppVersion;

    public TLRequestHelpGetAppChangelog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String getPrevAppVersion() {
        return prevAppVersion;
    }

    public TLRequestHelpGetAppChangelog setPrevAppVersion(String prevAppVersion) {
        this.prevAppVersion = prevAppVersion;
        return this;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(prevAppVersion, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        prevAppVersion = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.getAppChangelog#9010ef6f";
    }
}