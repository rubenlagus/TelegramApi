package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action chat edit title.
 */
public class TLMessageActionChatEditTitle extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb5a1ce5a;

    private String title;

    /**
     * Instantiates a new TL message action chat edit title.
     */
    public TLMessageActionChatEditTitle() {
        super();
    }

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.title = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messageActionChatEditTitle#b5a1ce5a";
    }
}