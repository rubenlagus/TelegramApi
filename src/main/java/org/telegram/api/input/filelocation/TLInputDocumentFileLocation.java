package org.telegram.api.input.filelocation;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input document file location.
 */
public class TLInputDocumentFileLocation extends TLAbsInputFileLocation {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x430f0724;

    private long id;
    private long accessHash;
    private int version;

    /**
     * Instantiates a new TL input document file location.
     */
    public TLInputDocumentFileLocation() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(version, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        version = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "inputDocumentFileLocation#430f0724";
    }
}