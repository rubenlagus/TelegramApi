package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 8:40
 */
public class MTMessageDetailedInfo extends TLObject {
    public static final int CLASS_ID = 0x276d3ec6;

    private long msgId;
    private long answerMsgId;
    private int bytes;
    private int state;

    public MTMessageDetailedInfo(long msgId, long answerMsgId, int bytes, int state) {
        this.msgId = msgId;
        this.answerMsgId = answerMsgId;
        this.bytes = bytes;
        this.state = state;
    }

    public MTMessageDetailedInfo() {

    }

    public long getMsgId() {
        return this.msgId;
    }

    public long getAnswerMsgId() {
        return this.answerMsgId;
    }

    public int getBytes() {
        return this.bytes;
    }

    public int getState() {
        return this.state;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.msgId, stream);
        writeLong(this.answerMsgId, stream);
        writeInt(this.bytes, stream);
        writeInt(this.state, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.msgId = readLong(stream);
        this.answerMsgId = readLong(stream);
        this.bytes = readInt(stream);
        this.state = readInt(stream);
    }

    @Override
    public String toString() {
        return "msg_detailed_info#276d3ec6";
    }
}
