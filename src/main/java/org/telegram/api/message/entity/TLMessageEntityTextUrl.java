package org.telegram.api.message.entity;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Representation of entities in a message
 * @date 19 of September of 2015
 */
public class TLMessageEntityTextUrl extends TLAbsMessageEntity {
    public static final int CLASS_ID = 0x76a6d327;

    private int offset;
    private int length;
    private String url;


    public TLMessageEntityTextUrl() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(offset, stream);
        StreamingUtils.writeInt(length, stream);
        StreamingUtils.writeTLString(url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.length = StreamingUtils.readInt(stream);
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "message.entity.MessageEntityTextUrl#76a6d327";
    }
}
