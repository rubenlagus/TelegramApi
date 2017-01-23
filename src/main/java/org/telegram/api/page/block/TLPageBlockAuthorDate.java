package org.telegram.api.page.block;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockAuthorDate extends TLObject {
    public static final int CLASS_ID = 0x3d5b64f2;

    private String author;
    private int publishedDate;

    public TLPageBlockAuthorDate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(author, stream);
        StreamingUtils.writeInt(publishedDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        author = StreamingUtils.readTLString(stream);
        publishedDate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "pageBlockAuthorDate#3d5b64f2";
    }
}
