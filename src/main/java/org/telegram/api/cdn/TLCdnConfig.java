package org.telegram.api.cdn;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLCdnConfig extends TLObject {
    public static final int CLASS_ID = 0x5725e40a;

    private TLVector<TLCdnPublicKey> publicKeys;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLCdnPublicKey> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(TLVector<TLCdnPublicKey> publicKeys) {
        this.publicKeys = publicKeys;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(publicKeys, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        publicKeys = StreamingUtils.readTLVector(stream, context, TLCdnPublicKey.class);
    }

    @Override
    public String toString() {
        return "cdnConfig#5725e40a";
    }
}
