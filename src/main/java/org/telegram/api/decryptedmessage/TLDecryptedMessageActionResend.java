package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action resend.
 */
public class TLDecryptedMessageActionResend extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x511110b0;

    private int startSeqNo;
    private int endSeqNo;

    /**
     * Instantiates a new TL decrypted message action resend.
     */
    public TLDecryptedMessageActionResend() {
    }

    /**
     * Instantiates a new TL decrypted message action resend.
     *
     * @param startSeqNo the start seq no
     * @param endSeqNo the end seq no
     */
    public TLDecryptedMessageActionResend(int startSeqNo, int endSeqNo) {
        this.startSeqNo = startSeqNo;
        this.endSeqNo = endSeqNo;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets start seq no.
     *
     * @return the start seq no
     */
    public int getStartSeqNo() {
        return this.startSeqNo;
    }

    /**
     * Sets start seq no.
     *
     * @param startSeqNo the start seq no
     */
    public void setStartSeqNo(int startSeqNo) {
        this.startSeqNo = startSeqNo;
    }

    /**
     * Gets end seq no.
     *
     * @return the end seq no
     */
    public int getEndSeqNo() {
        return this.endSeqNo;
    }

    /**
     * Sets end seq no.
     *
     * @param endSeqNo the end seq no
     */
    public void setEndSeqNo(int endSeqNo) {
        this.endSeqNo = endSeqNo;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.startSeqNo, stream);
        StreamingUtils.writeInt(this.endSeqNo, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.startSeqNo = StreamingUtils.readInt(stream);
        this.endSeqNo = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionResend#511110b0";
    }
}