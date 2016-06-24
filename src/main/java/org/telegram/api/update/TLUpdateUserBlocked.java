package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update user blocked.
 */
public class TLUpdateUserBlocked extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x80ece81a;

    private int userId;
    private boolean blocked;

    /**
     * Instantiates a new TL update user blocked.
     */
    public TLUpdateUserBlocked() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Is blocked.
     *
     * @return the boolean
     */
    public boolean isBlocked() {
        return this.blocked;
    }

    /**
     * Sets blocked.
     *
     * @param blocked the blocked
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLBool(this.blocked, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.blocked = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "updateUserBlocked#80ece81a";
    }
}