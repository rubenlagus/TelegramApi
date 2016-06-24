package org.telegram.api.input.encrypted.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input encrypted file big uploaded.
 */
public class TLInputEncryptedFileBigUploaded extends TLAbsInputEncryptedFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2dc173c8;

    /**
     * Instantiates a new TL input encrypted file big uploaded.
     */
    public TLInputEncryptedFileBigUploaded() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeInt(this.keyFingerprint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.keyFingerprint = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "inputEncryptedFileBigUploaded#2dc173c8";
    }
}