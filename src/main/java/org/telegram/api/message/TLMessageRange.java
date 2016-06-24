package org.telegram.api.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message range.
 */
public class TLMessageRange extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae30253;

    private int minId;
    private int maxId;

    /**
     * Instantiates a new TL message.
     */
    public TLMessageRange() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.minId, stream);
        StreamingUtils.writeInt(this.maxId, stream);

    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.minId = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "message.TLMessageRange#ae30253";
    }
}