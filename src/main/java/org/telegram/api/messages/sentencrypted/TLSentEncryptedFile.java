package org.telegram.api.messages.sentencrypted;

import org.telegram.api.encrypted.file.TLAbsEncryptedFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sent encrypted file.
 */
public class TLSentEncryptedFile extends TLAbsSentEncryptedMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9493ff32;

    /**
     * Instantiates a new TL sent encrypted file.
     */
    public TLSentEncryptedFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.date = StreamingUtils.readInt(stream);
        this.file = ((TLAbsEncryptedFile) StreamingUtils.readTLObject(stream, context));
    }

    @Override
    public String toString() {
        return "messages.sentEncryptedFile#9493ff32";
    }
}