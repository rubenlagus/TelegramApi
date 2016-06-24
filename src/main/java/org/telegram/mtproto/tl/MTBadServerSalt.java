package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeLong;

public class MTBadServerSalt extends MTBadMessage {

    public static final int CLASS_ID = 0xedab447b;


    public MTBadServerSalt(long messageId, int seqNo, int errorNo, long newSalt) {
        this.badMsgId = messageId;
        this.badMsqSeqno = seqNo;
        this.errorCode = errorNo;
        this.newServerSalt = newSalt;
    }

    public MTBadServerSalt() {
        this.badMsgId = 0L;
        this.badMsqSeqno = 0;
        this.errorCode = 0;
        this.newServerSalt = 0L;
    }

    public long getNewServerSalt() {
        return this.newServerSalt;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.badMsgId, stream);
        writeInt(this.badMsqSeqno, stream);
        writeInt(this.errorCode, stream);
        writeLong(this.newServerSalt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.badMsgId = readLong(stream);
        this.badMsqSeqno = readInt(stream);
        this.errorCode = readInt(stream);
        this.newServerSalt = readLong(stream);
    }

    @Override
    public String toString() {
        return "bad_server_salt#edab447b";
    }
}
