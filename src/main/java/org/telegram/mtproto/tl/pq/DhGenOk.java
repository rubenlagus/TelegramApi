package org.telegram.mtproto.tl.pq;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 7:20
 */
public class DhGenOk extends DhGenResult {
    public static final int CLASS_ID = 0x3bcbf734;

    public DhGenOk(byte[] nonce, byte[] serverNonce, byte[] newNonceHash) {
        super(nonce, serverNonce, newNonceHash);
    }

    public DhGenOk() {
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "dh_gen_ok#3bcbf734";
    }
}
