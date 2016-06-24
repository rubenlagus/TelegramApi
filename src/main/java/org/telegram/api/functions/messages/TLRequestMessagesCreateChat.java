package org.telegram.api.functions.messages;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesCreateChat extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cb126e;

    private TLVector<TLAbsInputUser> users;
    private String title;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesCreateChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsUpdates))
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsInputUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsInputUser> value) {
        this.users = value;
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
     * @param value the value
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLString(this.title, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.users = StreamingUtils.readTLVector(stream, context);
        this.title = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messages.createChat#9cb126e";
    }
}