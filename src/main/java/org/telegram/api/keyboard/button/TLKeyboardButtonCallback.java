package org.telegram.api.keyboard.button;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
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
public class TLKeyboardButtonCallback extends TLAbsKeyboardButton {
    public static final int CLASS_ID = 0x683a5e46;

    private TLBytes data;

    public TLKeyboardButtonCallback() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getData() {
        return data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLBytes(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.data = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "keyboardButtonCallback#683a5e46";
    }
}
