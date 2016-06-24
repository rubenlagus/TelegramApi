package org.telegram.mtproto.tl.pq;

import org.telegram.tl.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 6:27
 */
public abstract class ServerDhParams extends TLObject {
    public byte[] nonce;
    public byte[] serverNonce;
    public byte[] newNonceHash;
    public byte[] encryptedAnswer;

    public byte[] getNonce() {
        return this.nonce;
    }

    public void setNonce(byte[] nonce) {
        this.nonce = nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public void setServerNonce(byte[] serverNonce) {
        this.serverNonce = serverNonce;
    }

    public byte[] getNewNonceHash() {
        return this.newNonceHash;
    }

    public void setNewNonceHash(byte[] newNonceHash) {
        this.newNonceHash = newNonceHash;
    }

    public byte[] getEncryptedAnswer() {
        return this.encryptedAnswer;
    }

    public void setEncryptedAnswer(byte[] encryptedAnswer) {
        this.encryptedAnswer = encryptedAnswer;
    }

}
