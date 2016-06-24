package org.telegram.mtproto.tl;

import org.telegram.mtproto.util.BytesCache;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 20:46
 */
public class MTMessage extends TLObject {
    private long messageId;
    private int seqNo;
    private byte[] content;
    private int contentLen;

    public MTMessage(long messageId, int seqNo, byte[] content) {
        this(messageId, seqNo, content, content.length);
    }

    public MTMessage(long messageId, int seqNo, byte[] content, int contentLen) {
        this.messageId = messageId;
        this.seqNo = seqNo;
        this.content = content;
        this.contentLen = contentLen;
    }

    public MTMessage() {

    }

    @Override
    public int getClassId() {
        return 0;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getContentLen() {
        return this.contentLen;
    }

    public void setContentLen(int contentLen) {
        this.contentLen = contentLen;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.messageId, stream);
        writeInt(this.seqNo, stream);
        writeInt(this.content.length, stream);
        writeByteArray(this.content, 0, this.contentLen, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messageId = readLong(stream);
        this.seqNo = readInt(stream);
        int size = readInt(stream);
        this.content = BytesCache.getInstance().allocate(size);
        readBytes(this.content, 0, size, stream);
    }

    @Override
    public String toString() {
        return "message";
    }
}
