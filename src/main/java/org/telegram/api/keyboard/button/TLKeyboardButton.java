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
public class TLKeyboardButton extends TLAbsKeyboardButton {
    public static final int CLASS_ID = 0xa2fa4880;

    private String text;

    public TLKeyboardButton() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboard.TLKeyboardButton#a2fa4880";
    }
}
