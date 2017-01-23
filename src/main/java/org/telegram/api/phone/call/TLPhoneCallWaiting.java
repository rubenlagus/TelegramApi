package org.telegram.api.phone.call;

import org.telegram.api.phone.TLPhoneCallProtocol;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallWaiting extends TLObject {
    public static final int CLASS_ID = 0x1b8f4ad1;

    private static final int FLAG_RECEIVE_DATE  = 0x00000001; // 0

    private int flags;
    private long id;
    private long accessHash;
    private int date;
    private int adminId;
    private int participantId;
    private TLPhoneCallProtocol protocol;
    private int receiveDate;

    public TLPhoneCallWaiting() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public long getId() {
        return id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public int getDate() {
        return date;
    }

    public int getAdminId() {
        return adminId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public int getReceiveDate() {
        return receiveDate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(adminId, stream);
        StreamingUtils.writeInt(participantId, stream);
        StreamingUtils.writeTLObject(protocol, stream);
        if ((flags & FLAG_RECEIVE_DATE) != 0) {
            StreamingUtils.writeInt(receiveDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        date = StreamingUtils.readInt(stream);
        adminId = StreamingUtils.readInt(stream);
        participantId = StreamingUtils.readInt(stream);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
        if ((flags & FLAG_RECEIVE_DATE) != 0) {
            receiveDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "phoneCallWaiting#1b8f4ad1";
    }
}
