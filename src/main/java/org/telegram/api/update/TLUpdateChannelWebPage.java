package org.telegram.api.update;

import org.telegram.api.webpage.TLAbsWebPage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat user typing.
 */
public class TLUpdateChannelWebPage extends TLAbsUpdate implements TLChannelUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40771900;

    private int channelId;
    private TLAbsWebPage webPage;
    private int pts;
    private int ptsCount;

    public TLUpdateChannelWebPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    public TLAbsWebPage getWebPage() {
        return webPage;
    }

    @Override
    public int getPts() {
        return pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeTLObject(this.webPage, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.webPage = StreamingUtils.readTLObject(stream, context, TLAbsWebPage.class);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateChannelWebPage#40771900";
    }
}