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
public class TLCdnFile extends TLAbsCdnFile {
    public static final int CLASS_ID = 0xa99fca4f;

    private TLBytes bytes;

    public TLCdnFile() {
        super();
    }

    public TLBytes getBytes() {
        return bytes;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.cdnFile#a99fca4f";
    }
}
