package org.telegram.mtproto.tl;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief MTRpcAnswerDroppedRunning
 * @date 21/02/15
 */
public class MTRpcAnswerDropped extends TLObject {
    public static final int CLASS_ID = 0xa43ad8b7;

    private Long msg_id;
    private int seq_no;
    private int bytes;

    public MTRpcAnswerDropped() {
    }

    public MTRpcAnswerDropped(Long msg_id, int seq_no, int bytes) {
        this.msg_id = msg_id;
        this.seq_no = seq_no;
        this.bytes = bytes;
    }

    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.msg_id, stream);
        StreamingUtils.writeInt(this.seq_no, stream);
        StreamingUtils.writeInt(this.bytes, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.msg_id = StreamingUtils.readLong(stream);
        this.seq_no = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readInt(stream);
    }

    public Long getMsg_id() {
        return this.msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public int getSeq_no() {
        return this.seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    public int getBytes() {
        return this.bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "MTPRpcAnswerDropper#a43ad8b7";
    }
}
