package org.telegram.api.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message empty.
 */
public class TLMessageEmpty extends TLAbsMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x83e5de54;
    private int id;

    /**
     * Instantiates a new TL message empty.
     */
    public TLMessageEmpty() {
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

    @Override
    public int getChatId() {
        return 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messageEmpty#83e5de54";
    }
}
