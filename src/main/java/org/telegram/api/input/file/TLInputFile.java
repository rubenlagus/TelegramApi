package org.telegram.api.input.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input file.
 */
public class TLInputFile extends TLAbsInputFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf52ff27f;

    /**
     * The Md 5 checksum.
     */
    private String md5Checksum = "";

    /**
     * Instantiates a new TL input file.
     */
    public TLInputFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets md 5 checksum.
     *
     * @return the md 5 checksum
     */
    public String getMd5Checksum() {
        return this.md5Checksum;
    }

    /**
     * Sets md 5 checksum.
     *
     * @param md5Checksum the md 5 checksum
     */
    public void setMd5Checksum(String md5Checksum) {
        this.md5Checksum = md5Checksum;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeTLString(this.name, stream);
        StreamingUtils.writeTLString(this.md5Checksum, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.name = StreamingUtils.readTLString(stream);
        this.md5Checksum = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputFile#f52ff27f";
    }
}