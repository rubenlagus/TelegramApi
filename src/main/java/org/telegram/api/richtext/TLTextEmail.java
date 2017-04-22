package org.telegram.api.richtext;

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
public class TLTextEmail extends TLAbsRichText {
    public static final int CLASS_ID = 0xde5a0dd6;

    private TLAbsRichText text;
    private String email;

    public TLTextEmail() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getText() {
        return text;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(text, stream);
        StreamingUtils.writeTLString(email, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        email = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "textEmail#de5a0dd6";
    }
}
