package org.telegram.api.update;

import org.telegram.api.user.status.TLAbsUserStatus;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update user status.
 */
public class TLUpdateUserStatus extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1bfbd823;

    private int userId;
    private TLAbsUserStatus status;

    /**
     * Instantiates a new TL update user status.
     */
    public TLUpdateUserStatus() {
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
     * Gets status.
     *
     * @return the status
     */
    public TLAbsUserStatus getStatus() {
        return this.status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(TLAbsUserStatus status) {
        this.status = status;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.status, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.status = ((TLAbsUserStatus) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "updateUserStatus#1bfbd823";
    }
}