package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update message iD.
 */
public class TLUpdateMessageId extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4e90bfd6;

    private int id;
    private long randomId;

    /**
     * Instantiates a new TL update message iD.
     */
    public TLUpdateMessageId() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets random id.
     *
     * @return the random id
     */
    public long getRandomId() {
        return this.randomId;
    }

    /**
     * Sets random id.
     *
     * @param randomId the random id
     */
    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeLong(this.randomId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.randomId = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "updateMessageID#4e90bfd6";
    }
}