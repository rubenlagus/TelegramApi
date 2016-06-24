package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action abort key.
 */
public class TLDecryptedMessageActionAbortKey extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdd05ec6b;

    private long exchangeId;

    /**
     * Instantiates a new TL decrypted message action abort key.
     */
    public TLDecryptedMessageActionAbortKey() {
    }

    /**
     * Instantiates a new TL decrypted message action abort key.
     *
     * @param exchangeId the exchange id
     */
    public TLDecryptedMessageActionAbortKey(long exchangeId) {
        this.exchangeId = exchangeId;
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

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.exchangeId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.exchangeId = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionAbortKey#dd05ec6b";
    }
}