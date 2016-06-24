package org.telegram.api.updates;

import org.telegram.api.update.TLAbsUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update short.
 */
public class TLUpdateShort extends TLAbsUpdates {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x78d4dec1;

    private TLAbsUpdate update;
    private int date;

    /**
     * Instantiates a new TL update short.
     */
    public TLUpdateShort() {
        super();
    }

    /**
     * Gets update.
     *
     * @return the update
     */
    public TLAbsUpdate getUpdate() {
        return this.update;
    }

    /**
     * Sets update.
     *
     * @param update the update
     */
    public void setUpdate(TLAbsUpdate update) {
        this.update = update;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.update, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.update = ((TLAbsUpdate) StreamingUtils.readTLObject(stream, context));
        this.date = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateShort#78d4dec1";
    }
}