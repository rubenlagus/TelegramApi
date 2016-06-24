package org.telegram.api.input.chat;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Input channel
 * @date 19 of September of 2015
 */
public class TLInputChannel extends TLAbsInputChannel {
    public static final int CLASS_ID = 0xafeb712e;

    private int channelId;
    private long accessHash;


    public TLInputChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(channelId, stream);
        StreamingUtils.writeLong(accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "input.chat.TLInputChannel#afeb712e";
    }
}
