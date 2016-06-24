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
 * Time: 8:24
 */
public class MTPong extends TLObject {

    public static final int CLASS_ID = 0x347773c5;

    private long messageId;
    private long pingId;

    public MTPong(long messageId, long pingId) {
        this.messageId = messageId;
        this.pingId = pingId;
    }

    public MTPong() {
    }

    public long getMessageId() {
        return this.messageId;
    }

    public long getPingId() {
        return this.pingId;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.messageId, stream);
        writeLong(this.pingId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messageId = readLong(stream);
        this.pingId = readLong(stream);
    }

    @Override
    public String toString() {
        return "pong#347773c5";
    }
}
