package org.telegram.api.functions.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;
import org.telegram.tl.TLMethod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages received queue.
 */
public class TLRequestMessagesReceivedQueue extends TLMethod<TLLongVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x55a5bb66;

    private int maxQts;

    /**
     * Instantiates a new TL request messages received queue.
     */
    public TLRequestMessagesReceivedQueue() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLLongVector(stream, context);
    }

    /**
     * Gets max qts.
     *
     * @return the max qts
     */
    public int getMaxQts() {
        return this.maxQts;
    }

    /**
     * Sets max qts.
     *
     * @param value the value
     */
    public void setMaxQts(int value) {
        this.maxQts = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.maxQts, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.maxQts = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.receivedQueue#55a5bb66";
    }
}