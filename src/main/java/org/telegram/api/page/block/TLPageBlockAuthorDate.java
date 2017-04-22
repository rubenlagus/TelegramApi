package org.telegram.api.page.block;

import org.telegram.api.richtext.TLAbsRichText;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockAuthorDate extends TLAbsPageBlock {
    public static final int CLASS_ID = 0xbaafe5e0;

    private TLAbsRichText author;
    private int publishedDate;

    public TLPageBlockAuthorDate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getAuthor() {
        return author;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(author, stream);
        StreamingUtils.writeInt(publishedDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        author = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        publishedDate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "pageBlockAuthorDate#baafe5e0";
    }
}
