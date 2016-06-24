package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action accept key.
 */
public class TLDecryptedMessageActionAcceptKey extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6fe1735b;

    private long exchangeId;
    private TLBytes g_a;
    private long KeyFingerprint;

    /**
     * Instantiates a new TL decrypted message action accept key.
     */
    public TLDecryptedMessageActionAcceptKey() {
    }

    /**
     * Instantiates a new TL decrypted message action accept key.
     *
     * @param exchangeId the exchange id
     * @param g_a the g _ a
     * @param KeyFingerprint the key fingerprint
     */
    public TLDecryptedMessageActionAcceptKey(long exchangeId, TLBytes g_a, long KeyFingerprint) {
        this.exchangeId = exchangeId;
        this.g_a = g_a;
        this.KeyFingerprint = KeyFingerprint;
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
     * Gets g _ a.
     *
     * @return the g _ a
     */
    public TLBytes getG_a() {
        return this.g_a;
    }

    /**
     * Sets g _ a.
     *
     * @param g_a the g _ a
     */
    public void setG_a(TLBytes g_a) {
        this.g_a = g_a;
    }

    /**
     * Gets key fingerprint.
     *
     * @return the key fingerprint
     */
    public long getKeyFingerprint() {
        return this.KeyFingerprint;
    }

    /**
     * Sets key fingerprint.
     *
     * @param keyFingerprint the key fingerprint
     */
    public void setKeyFingerprint(long keyFingerprint) {
        this.KeyFingerprint = keyFingerprint;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.exchangeId, stream);
        StreamingUtils.writeTLBytes(this.g_a, stream);
        StreamingUtils.writeLong(this.KeyFingerprint, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.exchangeId = StreamingUtils.readLong(stream);
        this.g_a = StreamingUtils.readTLBytes(stream, context);
        this.KeyFingerprint = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionAcceptKey#6fe1735b";
    }
}