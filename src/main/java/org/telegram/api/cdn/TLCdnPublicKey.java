package org.telegram.api.cdn;

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
public class TLCdnPublicKey extends TLObject {
    public static final int CLASS_ID = 0xc982eaba;

    private int dcId;
    private String publicKey;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(dcId, stream);
        StreamingUtils.writeTLString(publicKey, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        dcId = StreamingUtils.readInt(stream);
        publicKey = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "cdnPublicKey#c982eaba";
    }
}
