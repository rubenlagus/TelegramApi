package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.readTLBytes;
import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeTLBytes;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 6:29
 */
public class ServerDhOk extends ServerDhParams {

    public static final int CLASS_ID = 0xd0e8075c;

    public ServerDhOk(byte[] nonce, byte[] serverNonce, byte[] encryptedAnswer) {
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.newNonceHash = null;
        this.encryptedAnswer = encryptedAnswer;
    }

    public ServerDhOk() {
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
        writeTLBytes(this.encryptedAnswer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.encryptedAnswer = readTLBytes(stream);
    }

    @Override
    public String toString() {
        return "server_DH_params_ok#d0e8075c";
    }
}
