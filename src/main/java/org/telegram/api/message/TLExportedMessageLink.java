package org.telegram.api.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 17 of March of 2016
 */
public class TLExportedMessageLink extends TLObject {
    public static final int CLASS_ID = 0x1f486803;

    private String link;

    public TLExportedMessageLink() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(link, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        link = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "message.exportedMessageLink#1f486803";
    }
}
