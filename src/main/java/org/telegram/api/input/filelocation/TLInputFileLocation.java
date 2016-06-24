package org.telegram.api.input.filelocation;

import org.telegram.api.file.location.TLFileLocation;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input file location.
 */
public class TLInputFileLocation extends TLAbsInputFileLocation {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x14637196;

    private long volumeId;
    private int localId;
    private long secret;

    /**
     * Instantiates a new TL input file location.
     */
    public TLInputFileLocation() {
        super();
    }

    /**
     * Instantiates a new TL input file location.
     *
     * @param fileLocation the file location
     */
    public TLInputFileLocation(TLFileLocation fileLocation) {
        super();
        this.volumeId = fileLocation.getVolumeId();
        this.localId = fileLocation.getLocalId();
        this.secret = fileLocation.getSecret();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets volume id.
     *
     * @return the volume id
     */
    public long getVolumeId() {
        return this.volumeId;
    }

    /**
     * Sets volume id.
     *
     * @param volumeId the volume id
     */
    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * Gets local id.
     *
     * @return the local id
     */
    public int getLocalId() {
        return this.localId;
    }

    /**
     * Sets local id.
     *
     * @param localId the local id
     */
    public void setLocalId(int localId) {
        this.localId = localId;
    }

    /**
     * Gets secret.
     *
     * @return the secret
     */
    public long getSecret() {
        return this.secret;
    }

    /**
     * Sets secret.
     *
     * @param secret the secret
     */
    public void setSecret(long secret) {
        this.secret = secret;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
        StreamingUtils.writeLong(this.secret, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
        this.secret = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "inputFileLocation#14637196";
    }
}