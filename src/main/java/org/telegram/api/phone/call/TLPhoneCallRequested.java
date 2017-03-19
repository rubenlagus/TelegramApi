package org.telegram.api.phone.call;

import org.telegram.api.phone.TLPhoneCallProtocol;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallRequested extends TLAbsPhoneCall {
    public static final int CLASS_ID = 0x83761ce4;

    private long id;
    private long accessHash;
    private int date;
    private int adminId;
    private int participantId;
    private TLBytes gAHash;
    private TLPhoneCallProtocol protocol;

    public TLPhoneCallRequested() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public TLBytes getgAHash() {
        return gAHash;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(adminId, stream);
        StreamingUtils.writeInt(participantId, stream);
        StreamingUtils.writeTLBytes(gAHash, stream);
        StreamingUtils.writeTLObject(protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        date = StreamingUtils.readInt(stream);
        adminId = StreamingUtils.readInt(stream);
        participantId = StreamingUtils.readInt(stream);
        gAHash = StreamingUtils.readTLBytes(stream, context);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phoneCallRequested#83761ce4";
    }
}
