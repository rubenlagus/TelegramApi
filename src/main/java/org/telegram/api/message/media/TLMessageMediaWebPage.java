package org.telegram.api.message.media;

import org.telegram.api.webpage.TLAbsWebPage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media web page.
 */
public class TLMessageMediaWebPage extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa32dd600;

    private TLAbsWebPage webPage;

    /**
     * Instantiates a new TL message media web page.
     */
    public TLMessageMediaWebPage() {
        super();
    }

    @Override
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
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.webPage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.webPage = ((TLAbsWebPage) StreamingUtils.readTLObject(stream, context));
    }

    @Override
    public String toString() {
        return "messageMediaDocument#a32dd600";
    }
}