package org.telegram.api.functions.auth;

import org.telegram.api.auth.TLAuthorization;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request auth import authorization.
 */
public class TLRequestAuthImportBotAuthorization extends TLMethod<TLAuthorization> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x67a3ff2c;

    private int flags;
    private int apiId;
    private String apiHash;
    private String botAuthToken;

    /**
     * Instantiates a new TL request auth import authorization.
     */
    public TLRequestAuthImportBotAuthorization() {
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

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getApiId() {
        return this.apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getApiHash() {
        return this.apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }

    public String getBotAuthToken() {
        return this.botAuthToken;
    }

    public void setBotAuthToken(String botAuthToken) {
        this.botAuthToken = botAuthToken;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.apiHash, stream);
        StreamingUtils.writeTLString(this.botAuthToken, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.apiId = StreamingUtils.readInt(stream);
        this.apiHash = StreamingUtils.readTLString(stream);
        this.botAuthToken = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "auth.importBotAuthorization#67a3ff2c";
    }
}