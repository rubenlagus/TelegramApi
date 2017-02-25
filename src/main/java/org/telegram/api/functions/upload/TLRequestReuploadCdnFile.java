package org.telegram.api.functions.upload;

import org.telegram.api.upload.file.TLAbsFile;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestReuploadCdnFile extends TLMethod<TLAbsFile> {
    public static final int CLASS_ID = 0xc459521d;

    private TLBytes fileToken;
    private int offset;
    private TLBytes requestToken;

    public TLRequestReuploadCdnFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsFile deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsFile))
            return (TLAbsFile) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsFile.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    public TLBytes getFileToken() {
        return fileToken;
    }

    public void setFileToken(TLBytes fileToken) {
        this.fileToken = fileToken;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeTLBytes(this.requestToken, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.offset = StreamingUtils.readInt(stream);
        this.requestToken = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "upload.reuploadCdnFile#c459521d";
    }
}