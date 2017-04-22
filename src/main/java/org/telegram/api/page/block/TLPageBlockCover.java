package org.telegram.api.page.block;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockCover extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x39f23300;

    private TLAbsPageBlock cover;

    public TLPageBlockCover() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPageBlock getCover() {
        return cover;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(cover, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        cover = StreamingUtils.readTLObject(stream, context, TLAbsPageBlock.class);
    }

    @Override
    public String toString() {
        return "pageBlockCover#39f23300";
    }
}
