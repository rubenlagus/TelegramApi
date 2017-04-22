package org.telegram.api.page.block;

import org.telegram.api.richtext.TLAbsRichText;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockCollage extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x8b31c4f;

    private TLVector<TLAbsPageBlock> items;
    private TLAbsRichText caption;

    public TLPageBlockCollage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageBlock> getItems() {
        return items;
    }

    public TLAbsRichText getCaption() {
        return caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(items, stream);
        StreamingUtils.writeTLObject(caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        items = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        caption = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockCollage#8b31c4f";
    }
}
