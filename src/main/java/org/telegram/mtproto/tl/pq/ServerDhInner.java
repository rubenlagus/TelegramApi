package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readTLBytes;
import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeTLBytes;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 6:56
 */
public class ServerDhInner extends TLObject {
    public static final int CLASS_ID = 0xb5890dba;

    protected byte[] nonce;
    protected byte[] serverNonce;
    protected int g;
    protected byte[] dhPrime;
    protected byte[] g_a;
    protected int serverTime;

    public ServerDhInner(byte[] nonce, byte[] serverNonce, int g, byte[] dhPrime, byte[] g_a, int serverTime) {
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.g = g;
        this.dhPrime = dhPrime;
        this.g_a = g_a;
        this.serverTime = serverTime;
    }

    public ServerDhInner() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "serverDhInner#b5890dba";
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public int getG() {
        return this.g;
    }

    public byte[] getDhPrime() {
        return this.dhPrime;
    }

    public byte[] getG_a() {
        return this.g_a;
    }

    public int getServerTime() {
        return this.serverTime;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeByteArray(this.nonce, stream);
        writeByteArray(this.serverNonce, stream);
        writeInt(this.g, stream);
        writeTLBytes(this.dhPrime, stream);
        writeTLBytes(this.g_a, stream);
        writeInt(this.serverTime, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.g = readInt(stream);
        this.dhPrime = readTLBytes(stream);
        this.g_a = readTLBytes(stream);
        this.serverTime = readInt(stream);
    }
}
