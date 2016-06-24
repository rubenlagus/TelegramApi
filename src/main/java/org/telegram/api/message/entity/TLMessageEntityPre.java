package org.telegram.api.message.entity;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Abstract representation of entities in a message
 * @date 19 of September of 2015
 */
public class TLMessageEntityPre extends TLAbsMessageEntity {
    public static final int CLASS_ID = 0x73924be0;

    private int offset;
    private int length;
    private String language;

    public TLMessageEntityPre() {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(offset, stream);
        StreamingUtils.writeInt(length, stream);
        StreamingUtils.writeTLString(language, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.length = StreamingUtils.readInt(stream);
        this.language = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "message.entity.MessageEntityPre#73924be0";
    }
}
