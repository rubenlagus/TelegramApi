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
public class TLPageBlockList extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x3a58c7f4;

    private Boolean ordered;
    private TLVector<TLAbsRichText> items;

    public TLPageBlockList() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public TLVector<TLAbsRichText> getItems() {
        return items;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBool(ordered, stream);
        StreamingUtils.writeTLVector(items, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        ordered = StreamingUtils.readTLBool(stream);
        items = StreamingUtils.readTLVector(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockList#3a58c7f4";
    }
}
