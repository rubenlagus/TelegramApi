package org.telegram.api.functions.bots;

import org.telegram.api.TLDataJSON;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestBotsSendCustomRequest extends TLMethod<TLDataJSON> {
    public static final int CLASS_ID = 0xaa2769ed;

    private String customMethod;
    private TLDataJSON params;

    public TLRequestBotsSendCustomRequest() {
        super();
    }

    @Override
    public TLDataJSON deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLDataJSON)) {
            return (TLDataJSON) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLDataJSON.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public String getCustomMethod() {
        return customMethod;
    }

    public void setCustomMethod(String customMethod) {
        this.customMethod = customMethod;
    }

    public TLDataJSON getParams() {
        return params;
    }

    public void setParams(TLDataJSON params) {
        this.params = params;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(customMethod, stream);
        StreamingUtils.writeTLObject(params, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        customMethod = StreamingUtils.readTLString(stream);
        params = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "bots.sendCustomRequest#aa2769ed";
    }
}
