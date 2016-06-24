package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action request key.
 */
public class TLDecryptedMessageActionRequestKey extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3c9611b;
    private long exchangeId;
    private TLBytes g_a;

    /**
     * Instantiates a new TL decrypted message action request key.
     */
    public TLDecryptedMessageActionRequestKey() {
    }

    /**
     * Instantiates a new TL decrypted message action request key.
     *
     * @param exchangeId the exchange id
     * @param g_a the g _ a
     */
    public TLDecryptedMessageActionRequestKey(long exchangeId, TLBytes g_a) {
        this.exchangeId = exchangeId;
        this.g_a = g_a;
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

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.exchangeId, stream);
        StreamingUtils.writeTLBytes(this.g_a, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.exchangeId = StreamingUtils.readLong(stream);
        this.g_a = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionRequestKey#f3c9611b";
    }
}