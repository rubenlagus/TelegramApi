package org.telegram.api.chat.channel;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel information
 * @date 18 of September of 2015
 */
public class TLChannelForbidden extends TLAbsChat {
    public static final int CLASS_ID = 0x2d85832c;

    private long accessHash;
    private String title;

    public TLChannelForbidden() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeTLString(title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "channel.TLChannelForbidden#2d85832c";
    }
}
