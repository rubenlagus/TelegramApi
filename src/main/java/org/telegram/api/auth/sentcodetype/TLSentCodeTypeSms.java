package org.telegram.api.auth.sentcodetype;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 16 of March of 2016
 */
public class TLSentCodeTypeSms extends TLAbsSentCodeType {
    public static final int CLASS_ID = 0xc000bba2;

    private int length;

    public TLSentCodeTypeSms() {
        super();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(length, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        length = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "auth.sentCodeTypeSms#c000bba2";
    }
}
