package org.telegram.api.upload.cdn;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLCdnFileReuploadNeeded extends TLAbsCdnFile {
    public static final int CLASS_ID = 0xeea8e46e;

    private TLBytes requestToken;

    public TLCdnFileReuploadNeeded() {
        super();
    }

    public TLBytes getRequestToken() {
        return requestToken;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(requestToken, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        requestToken = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.cdnFileReuploadNeeded#eea8e46e";
    }
}
