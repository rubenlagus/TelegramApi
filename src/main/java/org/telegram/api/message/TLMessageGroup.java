package org.telegram.api.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message group.
 */
public class TLMessageGroup extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe8346f53;

    private int minId;
    private int maxId;
    private int date;

    /**
     * Instantiates a new TL message group
     */
    public TLMessageGroup() {
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.minId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.date, stream);

    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.minId = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "message.TLMessageGroup#e8346f53";
    }
}