package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.writeByteArray;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 6:31
 */
public class ServerDhFailure extends ServerDhParams {
    public static final int CLASS_ID = 0x79cb045d;

    public ServerDhFailure(byte[] nonce, byte[] serverNonce, byte[] newNonceHash) {
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.newNonceHash = newNonceHash;
        this.encryptedAnswer = null;
    }

    public ServerDhFailure() {
        this.nonce = null;
        this.serverNonce = null;
        this.newNonceHash = null;
        this.encryptedAnswer = null;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeByteArray(this.nonce, stream);
        writeByteArray(this.serverNonce, stream);
        writeByteArray(this.newNonceHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.newNonceHash = readBytes(16, stream);
    }

    @Override
    public String toString() {
        return "server_DH_params_fail#" + CLASS_ID;
    }
}
