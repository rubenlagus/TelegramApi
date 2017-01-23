package org.telegram.api.phone.call;

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
public class TLPhoneCallDiscarded extends TLObject {
    public static final int CLASS_ID = 0xcc3740bd;

    private long id;

    public TLPhoneCallDiscarded() {
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
        return "phoneCallDiscarded#cc3740bd";
    }
}
