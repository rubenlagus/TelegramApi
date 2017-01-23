package org.telegram.api.input.phonecall;

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
public class TLInputPhoneCall extends TLObject {
    public static final int CLASS_ID = 0x1e36fded;

    private long id;
    private long accessHash;

    public TLInputPhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputPhoneCall#1e36fded";
    }
}
