package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.readTLBytes;
import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeLong;
import static org.telegram.tl.StreamingUtils.writeTLBytes;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 7:32
 */
public class ClientDhInner extends TLObject {
    public static final int CLASS_ID = 0x6643b654;
    protected byte[] nonce;
    protected byte[] serverNonce;
    protected long retryId;
    protected byte[] gb;

    public ClientDhInner(byte[] nonce, byte[] serverNonce, long retryId, byte[] gb) {
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.retryId = retryId;
        this.gb = gb;
    }

    public ClientDhInner() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "clientDhInner#6643b654";
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public long getRetryId() {
        return this.retryId;
    }

    public byte[] getGb() {
        return this.gb;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeByteArray(this.nonce, stream);
        writeByteArray(this.serverNonce, stream);
        writeLong(this.retryId, stream);
        writeTLBytes(this.gb, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.retryId = readLong(stream);
        this.gb = readTLBytes(stream);
    }
}
