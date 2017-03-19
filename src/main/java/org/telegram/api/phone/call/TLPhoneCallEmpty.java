package org.telegram.api.phone.call;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallEmpty extends TLAbsPhoneCall {
    public static final int CLASS_ID = 0x5366c915;

    private long id;

    public TLPhoneCallEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "phoneCallEmpty#5366c915";
    }
}
