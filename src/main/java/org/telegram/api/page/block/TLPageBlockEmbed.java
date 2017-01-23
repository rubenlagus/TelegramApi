package org.telegram.api.page.block;

import org.telegram.api.richtext.TLAbsRichText;
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
public class TLPageBlockEmbed extends TLObject {
    public static final int CLASS_ID = 0xd935d8fb;

    private static final int FLAG_FULL_WIDTH       = 0x00000001; // 0
    private static final int FLAG_URL              = 0x00000002; // 1
    private static final int FLAG_HTML             = 0x00000004; // 2
    private static final int FLAG_ALLOW_SCROLLING  = 0x00000008; // 3

    private int flags;
    private String url;
    private String html;
    private int w;
    private int h;
    private TLAbsRichText caption;

    public TLPageBlockEmbed() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml() {
        return html;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public TLAbsRichText getCaption() {
        return caption;
    }

    public boolean isFullWidth() {
        return (flags & FLAG_FULL_WIDTH) != 0;
    }

    public boolean allowScrolling() {
        return (flags & FLAG_ALLOW_SCROLLING) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(url, stream);
        }
        if ((flags & FLAG_HTML) != 0) {
            StreamingUtils.writeTLString(html, stream);
        }
        StreamingUtils.writeInt(w, stream);
        StreamingUtils.writeInt(h, stream);
        StreamingUtils.writeTLObject(caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_URL) != 0) {
            url = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_HTML) != 0) {
            html = StreamingUtils.readTLString(stream);
        }
        w = StreamingUtils.readInt(stream);
        h = StreamingUtils.readInt(stream);
        caption = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockEmbed#d935d8fb";
    }
}
