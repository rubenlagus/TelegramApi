package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action channel create.
 */
public class TLMessageActionChannelCreate extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95d2ac92;

    private String title;

    /**
     * Instantiates a new TL message action chat channel.
     */
    public TLMessageActionChannelCreate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageActionChannelCreate#95d2ac92";
    }
}