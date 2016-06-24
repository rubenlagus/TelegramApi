package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth import authorization.
 */
public class TLRequestAuthImportAuthorization extends TLMethod<TLAuthorization> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe3ef9613;

    private int id;
    private TLBytes bytes;

    /**
     * Instantiates a new TL request auth import authorization.
     */
    public TLRequestAuthImportAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAuthorization deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAuthorization))
            return (TLAuthorization) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets bytes.
     *
     * @return the bytes
     */
    public TLBytes getBytes() {
        return this.bytes;
    }

    /**
     * Sets bytes.
     *
     * @param value the value
     */
    public void setBytes(TLBytes value) {
        this.bytes = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "auth.importAuthorization#e3ef9613";
    }
}