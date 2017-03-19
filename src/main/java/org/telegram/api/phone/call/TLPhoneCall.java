package org.telegram.api.phone.call;

import org.telegram.api.phone.TLPhoneCallProtocol;
import org.telegram.api.phone.TLPhoneConnection;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCall extends TLAbsPhoneCall {
    public static final int CLASS_ID = 0xffe6ab67;

    private long id;
    private long accessHash;
    private int date;
    private int adminId;
    private int participantId;
    private TLBytes gAorB;
    private long keyFingerprint;
    private TLPhoneCallProtocol protocol;
    private TLPhoneConnection connection;
    private TLVector<TLPhoneConnection> alternativeConnections;
    private int startDate;

    public TLPhoneCall() {
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

    public TLBytes getgAorB() {
        return gAorB;
    }

    public long getKeyFingerprint() {
        return keyFingerprint;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public TLPhoneConnection getConnection() {
        return connection;
    }

    public TLVector<TLPhoneConnection> getAlternativeConnections() {
        return alternativeConnections;
    }

    public int getStartDate() {
        return startDate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(adminId, stream);
        StreamingUtils.writeInt(participantId, stream);
        StreamingUtils.writeTLBytes(gAorB, stream);
        StreamingUtils.writeLong(keyFingerprint, stream);
        StreamingUtils.writeTLObject(protocol, stream);
        StreamingUtils.writeTLObject(connection, stream);
        StreamingUtils.writeTLVector(alternativeConnections, stream);
        StreamingUtils.writeInt(startDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        date = StreamingUtils.readInt(stream);
        adminId = StreamingUtils.readInt(stream);
        participantId = StreamingUtils.readInt(stream);
        gAorB = StreamingUtils.readTLBytes(stream, context);
        keyFingerprint = StreamingUtils.readLong(stream);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
        connection = StreamingUtils.readTLObject(stream, context, TLPhoneConnection.class);
        alternativeConnections = StreamingUtils.readTLVector(stream, context, TLPhoneConnection.class);
        startDate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "phoneCall#ffe6ab67";
    }
}
