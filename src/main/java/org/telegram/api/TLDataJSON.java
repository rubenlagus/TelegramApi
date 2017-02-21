package org.telegram.api;

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
public class TLDataJSON extends TLObject {
    public static final int CLASS_ID = 0x7d748d04;

    private String data;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getData() {
        return data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        data = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "dataJSON#7d748d04";
    }
}
