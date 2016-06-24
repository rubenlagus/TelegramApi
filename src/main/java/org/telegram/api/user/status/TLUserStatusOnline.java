package org.telegram.api.user.status;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user status online.
 */
public class TLUserStatusOnline extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedb93949;

    private int expires;

    /**
     * Instantiates a new TL user status online.
     */
    public TLUserStatusOnline() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets expires.
     *
     * @return the expires
     */
    public int getExpires() {
        return this.expires;
    }

    /**
     * Sets expires.
     *
     * @param expires the expires
     */
    public void setExpires(int expires) {
        this.expires = expires;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.expires = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "userStatusOnline#edb93949";
    }
}