package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLExportedAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth export authorization.
 */
public class TLRequestAuthExportAuthorization extends TLMethod<TLExportedAuthorization> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe5bfffcd;

    private int dcId;

    /**
     * Instantiates a new TL request auth export authorization.
     */
    public TLRequestAuthExportAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLExportedAuthorization deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLExportedAuthorization))
            return (TLExportedAuthorization) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLExportedAuthorization, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets dc id.
     *
     * @return the dc id
     */
    public int getDcId() {
        return this.dcId;
    }

    /**
     * Sets dc id.
     *
     * @param value the value
     */
    public void setDcId(int value) {
        this.dcId = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "auth.exportAuthorization#e5bfffcd";
    }
}