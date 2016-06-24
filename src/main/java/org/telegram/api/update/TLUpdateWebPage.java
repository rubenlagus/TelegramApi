package org.telegram.api.update;

import org.telegram.api.webpage.TLAbsWebPage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update web page.
 */
public class TLUpdateWebPage extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f891213;

    private TLAbsWebPage webPage;
    private int pts;
    private int ptsCount;

    /**
     * Instantiates a new TL update web page.
     */
    public TLUpdateWebPage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets web page.
     *
     * @return the web page
     */
    public TLAbsWebPage getWebPage() {
        return this.webPage;
    }

    /**
     * Sets web page.
     *
     * @param webPage the web page
     */
    public void setWebPage(TLAbsWebPage webPage) {
        this.webPage = webPage;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.webPage, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.webPage = (TLAbsWebPage) StreamingUtils.readTLObject(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateWebPage#7f891213";
    }
}