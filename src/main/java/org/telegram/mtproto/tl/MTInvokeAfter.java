package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created by Ruben Bermudez on 16.12.13.
 */
public class MTInvokeAfter extends TLObject {
    public static final int CLASS_ID = 0xcb9f372d;

    private long dependMsgId;
    private byte[] request;

    public MTInvokeAfter(long dependMsgId, byte[] request) {
        this.dependMsgId = dependMsgId;
        this.request = request;
    }

    public long getDependMsgId() {
        return this.dependMsgId;
    }

    public byte[] getRequest() {
        return this.request;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "mTInvokeAfter#cb9f372d";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.dependMsgId, stream);
        writeByteArray(this.request, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        throw new UnsupportedOperationException("Unable to deserialize invokeAfterMsg#" + CLASS_ID);
    }
}
