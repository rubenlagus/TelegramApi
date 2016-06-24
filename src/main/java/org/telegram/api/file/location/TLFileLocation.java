package org.telegram.api.file.location;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL file location.
 */
public class TLFileLocation extends TLAbsFileLocation {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x53d69076;

    private int dcId;
    private long volumeId;
    private int localId;
    private long secret;

    /**
     * Instantiates a new TL file location.
     */
    public TLFileLocation() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets dc id.
     *
     * @return the dc id
     */
    public int getDcId() {
        return this.dcId;
    }

    /**
     * Sets dc id.
     *
     * @param dcId the dc id
     */
    public void setDcId(int dcId) {
        this.dcId = dcId;
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
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
        StreamingUtils.writeLong(this.secret, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
        this.secret = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "fileLocation#53d69076";
    }
}