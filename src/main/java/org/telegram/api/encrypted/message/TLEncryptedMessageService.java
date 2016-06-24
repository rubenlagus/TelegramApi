package org.telegram.api.encrypted.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL encrypted message service.
 */
public class TLEncryptedMessageService extends TLAbsEncryptedMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x23734b06;

    /**
     * Instantiates a new TL encrypted message service.
     */
    public TLEncryptedMessageService() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.randomId = StreamingUtils.readLong(stream);
        this.chatId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "encryptedMessageService#23734b06";
    }
}