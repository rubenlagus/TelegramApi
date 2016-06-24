package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action chat add user.
 */
public class TLMessageActionChatAddUser extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x488a7337;

    private TLIntVector users;

    /**
     * Instantiates a new TL message action chat add user.
     */
    public TLMessageActionChatAddUser() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector getUsers() {
        return users;
    }

    public void setUsers(TLIntVector users) {
        this.users = users;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.users = StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "messageActionChatAddUser#488a7337";
    }
}