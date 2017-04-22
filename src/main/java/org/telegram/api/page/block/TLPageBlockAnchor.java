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
public class TLPageBlockAnchor extends TLAbsPageBlock {
    public static final int CLASS_ID = 0xce0d37b0;

    private String name;

    public TLPageBlockAnchor() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(name, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        name = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "pageBlockAnchor#ce0d37b0";
    }
}
