package org.telegram.api.functions.upload;

import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestReuploadCdnFile extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0x2e7a2020;

    private TLBytes fileToken;
    private TLBytes requestToken;

    public TLRequestReuploadCdnFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    public TLBytes getFileToken() {
        return fileToken;
    }

    public void setFileToken(TLBytes fileToken) {
        this.fileToken = fileToken;
    }

    public TLBytes getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(TLBytes requestToken) {
        this.requestToken = requestToken;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeTLBytes(this.requestToken, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.requestToken = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "upload.reuploadCdnFile#2e7a2020";
    }
}