package org.telegram.api.richtext;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLTextConcat extends TLAbsRichText {
    public static final int CLASS_ID = 0x7e6260d7;

    private TLVector<TLAbsRichText> texts;

    public TLTextConcat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsRichText> getTexts() {
        return texts;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(texts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        texts = StreamingUtils.readTLVector(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "textConcat#7e6260d7";
    }
}
