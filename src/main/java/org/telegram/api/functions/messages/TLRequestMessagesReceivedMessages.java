package org.telegram.api.functions.messages;

import org.telegram.api.TLReceivedNotifyMessage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages received messages.
 */
public class TLRequestMessagesReceivedMessages extends TLMethod<TLVector<TLReceivedNotifyMessage>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5a954c0;

    private int maxId;

    /**
     * Instantiates a new TL request messages received messages.
     */
    public TLRequestMessagesReceivedMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLReceivedNotifyMessage> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return (TLVector<TLReceivedNotifyMessage>) StreamingUtils.readTLVector(stream, context);
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public int getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param value the value
     */
    public void setMaxId(int value) {
        this.maxId = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.maxId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.maxId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.receivedMessages#5a954c0";
    }
}