package org.telegram.api.input.encrypted.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input encrypted file uploaded.
 */
public class TLInputEncryptedFileUploaded extends TLAbsInputEncryptedFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64bd0306;

    /**
     * Instantiates a new TL input encrypted file uploaded.
     */
    public TLInputEncryptedFileUploaded() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeTLString(this.md5Checksum, stream);
        StreamingUtils.writeInt(this.keyFingerprint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.md5Checksum = StreamingUtils.readTLString(stream);
        this.keyFingerprint = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "inputEncryptedFileUploaded#64bd0306";
    }
}