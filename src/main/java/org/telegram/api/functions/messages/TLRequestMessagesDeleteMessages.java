package org.telegram.api.functions.messages;

import org.telegram.api.messages.TLAffectedMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesDeleteMessages extends TLMethod<TLAffectedMessages> {
    public static final int CLASS_ID = 0xe58e95d2;

    private static final int FLAG_REVOKE      = 0x00000001; // 0

    private int flags;
    private TLIntVector id;

    public TLRequestMessagesDeleteMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAffectedMessages))
            return (TLAffectedMessages) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAffectedMessages, got: " + res.getClass().getCanonicalName());
    }


    public TLIntVector getId() {
        return this.id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    private void setRevoke(boolean revoke) {
        if (revoke) {
            this.flags |= FLAG_REVOKE;
        } else {
            this.flags &= ~FLAG_REVOKE;
        }
    }

    private boolean isRevoke() {
        return (flags & FLAG_REVOKE) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLVector(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "messages.deleteMessages#e58e95d2";
    }
}