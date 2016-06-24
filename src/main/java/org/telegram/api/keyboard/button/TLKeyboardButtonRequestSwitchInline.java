package org.telegram.api.keyboard.button;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represetation of a button in keyboard
 * @date 07 of July of 2015
 */
public class TLKeyboardButtonRequestSwitchInline extends TLAbsKeyboardButton {
    public static final int CLASS_ID = 0xfc796b3f;

    private String query;

    public TLKeyboardButtonRequestSwitchInline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLString(query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.query = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonSwitchInline#ea1b7a14";
    }
}
