package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action commit key.
 */
public class TLDecryptedMessageActionCommitKey extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xec2e0b9b;

    private long exchangeId;
    private long keyFingerprint;

    /**
     * Instantiates a new TL decrypted message action commit key.
     */
    public TLDecryptedMessageActionCommitKey() {
    }

    /**
     * Instantiates a new TL decrypted message action commit key.
     *
     * @param exchangeId the exchange id
     * @param keyFingerprint the key fingerprint
     */
    public TLDecryptedMessageActionCommitKey(long exchangeId, long keyFingerprint) {
        this.exchangeId = exchangeId;
        this.keyFingerprint = keyFingerprint;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets exchange id.
     *
     * @return the exchange id
     */
    public long getExchangeId() {
        return this.exchangeId;
    }

    /**
     * Sets exchange id.
     *
     * @param exchangeId the exchange id
     */
    public void setExchangeId(long exchangeId) {
        this.exchangeId = exchangeId;
    }

    /**
     * Gets key fingerprint.
     *
     * @return the key fingerprint
     */
    public long getKeyFingerprint() {
        return this.keyFingerprint;
    }

    /**
     * Sets key fingerprint.
     *
     * @param keyFingerprint the key fingerprint
     */
    public void setKeyFingerprint(long keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.exchangeId, stream);
        StreamingUtils.writeLong(this.keyFingerprint, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.exchangeId = StreamingUtils.readLong(stream);
        this.keyFingerprint = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionCommitKey#ec2e0b9b";
    }
}