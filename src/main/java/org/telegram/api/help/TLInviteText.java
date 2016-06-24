package org.telegram.api.help;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL invite text.
 */
public class TLInviteText extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x18cb9f78;

    private String message;

    /**
     * Instantiates a new TL invite text.
     */
    public TLInviteText() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param value the value
     */
    public void setMessage(String value) {
        this.message = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.message = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.inviteText#18cb9f78";
    }
}