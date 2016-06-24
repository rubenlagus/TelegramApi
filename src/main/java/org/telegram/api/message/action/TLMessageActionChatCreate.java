package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action chat create.
 */
public class TLMessageActionChatCreate extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa6638b9a;

    private String title;
    private TLIntVector users;

    /**
     * Instantiates a new TL message action chat create.
     */
    public TLMessageActionChatCreate() {
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

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLIntVector getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(TLIntVector users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.title = StreamingUtils.readTLString(stream);
        this.users = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messageActionChatCreate#a6638b9a";
    }
}