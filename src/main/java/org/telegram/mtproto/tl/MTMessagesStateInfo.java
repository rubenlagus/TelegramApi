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
 * @brief Informational Message regarding Status of Messages
 * @date 20/02/15
 */
public class MTMessagesStateInfo extends TLObject {
    public static final int CLASS_ID = 0x04deb57d;

    private Long req_msg_id;
    private String info;

    public MTMessagesStateInfo() {
    }

    public MTMessagesStateInfo(Long reqMsgId, String info) {
        this.req_msg_id = reqMsgId;
        this.info = info;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.req_msg_id, stream);
        StreamingUtils.writeTLString(this.info, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.req_msg_id = StreamingUtils.readLong(stream);
        this.info = StreamingUtils.readTLString(stream);
    }

    public Long getReqMsgId() {
        return this.req_msg_id;
    }

    public void setReqMsgId(Long req_msg_id) {
        this.req_msg_id = req_msg_id;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "mtMessagesStateInfo#04deb57d";
    }
}
