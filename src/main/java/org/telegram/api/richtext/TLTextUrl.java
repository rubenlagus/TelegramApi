package org.telegram.api.richtext;

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
public class TLTextUrl extends TLAbsRichText {
    public static final int CLASS_ID = 0x3c2884c1;

    private TLAbsRichText text;
    private String url;
    private long webpageId;

    public TLTextUrl() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public long getWebpageId() {
        return webpageId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(text, stream);
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeLong(webpageId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        url = StreamingUtils.readTLString(stream);
        webpageId = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "textUrl#3c2884c1";
    }
}
