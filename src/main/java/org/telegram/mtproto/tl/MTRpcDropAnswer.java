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
 * Date: 07.11.13
 * Time: 8:50
 */
public class MTRpcDropAnswer extends TLObject {
    public static final int CLASS_ID = 0x58e4a740;

    private Long req_msg_id;

    public MTRpcDropAnswer(Long req_msg_id) {
        this.req_msg_id = req_msg_id;
    }

    public MTRpcDropAnswer() {
    }

    public Long getReq_msg_id() {
        return this.req_msg_id;
    }

    public void setReq_msg_id(Long req_msg_id) {
        this.req_msg_id = req_msg_id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.req_msg_id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.req_msg_id = readLong(stream);
    }

    @Override
    public String toString() {
        return "msg_resend_req#58e4a740";
    }
}
