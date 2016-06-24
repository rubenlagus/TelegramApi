package org.telegram.api.functions.messages;

import org.telegram.api.input.encrypted.TLInputEncryptedChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages read encrypted history.
 */
public class TLRequestMessagesReadEncryptedHistory extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f4b690a;

    private TLInputEncryptedChat peer;
    private int maxDate;

    /**
     * Instantiates a new TL request messages read encrypted history.
     */
    public TLRequestMessagesReadEncryptedHistory() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    /**
     * Gets max date.
     *
     * @return the max date
     */
    public int getMaxDate() {
        return this.maxDate;
    }

    /**
     * Sets max date.
     *
     * @param value the value
     */
    public void setMaxDate(int value) {
        this.maxDate = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxDate, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLInputEncryptedChat) StreamingUtils.readTLObject(stream, context));
        this.maxDate = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.readEncryptedHistory#7f4b690a";
    }
}