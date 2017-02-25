package org.telegram.api.input.paymentapi;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLInputWebFileLocation extends TLObject {
    public static final int CLASS_ID = 0xc239d686;

    private String url;
    private long accessHash;

    public TLInputWebFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeLong(accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        url = StreamingUtils.readTLString(stream);
        accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputWebFileLocation#c239d686";
    }
}
