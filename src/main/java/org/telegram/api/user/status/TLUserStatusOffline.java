package org.telegram.api.user.status;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user status offline.
 */
public class TLUserStatusOffline extends TLAbsUserStatus {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8c703f;

    private int wasOnline;

    /**
     * Instantiates a new TL user status offline.
     */
    public TLUserStatusOffline() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets was online.
     *
     * @return the was online
     */
    public int getWasOnline() {
        return this.wasOnline;
    }

    /**
     * Sets was online.
     *
     * @param wasOnline the was online
     */
    public void setWasOnline(int wasOnline) {
        this.wasOnline = wasOnline;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.wasOnline, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.wasOnline = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "userStatusOffline#8c703f";
    }
}