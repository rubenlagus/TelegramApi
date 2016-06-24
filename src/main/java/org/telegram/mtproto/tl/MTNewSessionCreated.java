package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:35
 */
public class MTNewSessionCreated extends TLObject {

    public static final int CLASS_ID = 0x9ec20908;

    private long firstMsgId;
    private long uniqId;
    private long serverSalt;

    public MTNewSessionCreated(long firstMsgId, long uniqId, long serverSalt) {
        this.firstMsgId = firstMsgId;
        this.uniqId = uniqId;
        this.serverSalt = serverSalt;
    }

    public MTNewSessionCreated() {

    }

    public long getFirstMsgId() {
        return this.firstMsgId;
    }

    public long getUniqId() {
        return this.uniqId;
    }

    public long getServerSalt() {
        return this.serverSalt;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.firstMsgId, stream);
        writeLong(this.uniqId, stream);
        writeLong(this.serverSalt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.firstMsgId = readLong(stream);
        this.uniqId = readLong(stream);
        this.serverSalt = readLong(stream);
    }

    @Override
    public String toString() {
        return "new_session_created#9ec20908";
    }
}
