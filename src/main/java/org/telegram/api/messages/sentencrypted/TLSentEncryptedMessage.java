package org.telegram.api.messages.sentencrypted;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sent encrypted message.
 */
public class TLSentEncryptedMessage extends TLAbsSentEncryptedMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x560f8935;

    /**
     * Instantiates a new TL sent encrypted message.
     */
    public TLSentEncryptedMessage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.date, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.date = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.sentEncryptedMessage#560f8935";
    }
}