package org.telegram.api.input.sticker.media;

import org.telegram.api.input.document.TLAbsInputDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 26 of September of 2016
 */
public class TLInputStickeredMediaDocument extends TLAbsInputStickeredMedia {
    public static final int CLASS_ID = 0x438865b;

    private TLAbsInputDocument id;

    public TLAbsInputDocument getId() {
        return id;
    }

    public void setId(TLAbsInputDocument id) {
        this.id = id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
    }

    @Override
    public String toString() {
        return "inputStickeredMediaDocument#438865b";
    }
}
