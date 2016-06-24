package org.telegram.api.functions.users;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request users get users.
 */
public class TLRequestUsersGetUsers extends TLMethod<TLVector<TLAbsUser>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd91a548;

    /**
     * The Id.
     */
    protected TLVector<TLAbsInputUser> id;

    /**
     * Instantiates a new TL request users get users.
     */
    public TLRequestUsersGetUsers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsUser> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLVector<TLAbsInputUser> getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLVector<TLAbsInputUser> value) {
        this.id = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "users.getUsers#d91a548";
    }
}