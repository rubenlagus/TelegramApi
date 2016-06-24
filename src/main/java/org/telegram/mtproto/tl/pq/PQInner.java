package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

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
 * Time: 6:20
 */
public class PQInner extends TLObject {
    public static final int CLASS_ID = 0x83c95aec;

    protected byte[] pq;
    protected byte[] p;
    protected byte[] q;
    protected byte[] nonce;
    protected byte[] serverNonce;
    protected byte[] newNonce;

    public PQInner(byte[] pq, byte[] p, byte[] q, byte[] nonce, byte[] serverNonce, byte[] newNonce) {
        this.pq = pq;
        this.p = p;
        this.q = q;
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.newNonce = newNonce;
    }

    public PQInner() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "pQInner#83c95aec";
    }

    public byte[] getPq() {
        return this.pq;
    }

    public byte[] getP() {
        return this.p;
    }

    public byte[] getQ() {
        return this.q;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public byte[] getNewNonce() {
        return this.newNonce;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLBytes(this.pq, stream);
        writeTLBytes(this.p, stream);
        writeTLBytes(this.q, stream);
        writeByteArray(this.nonce, stream);
        writeByteArray(this.serverNonce, stream);
        writeByteArray(this.newNonce, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.pq = readTLBytes(stream);
        this.p = readTLBytes(stream);
        this.q = readTLBytes(stream);
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.newNonce = readBytes(32, stream);
    }
}
