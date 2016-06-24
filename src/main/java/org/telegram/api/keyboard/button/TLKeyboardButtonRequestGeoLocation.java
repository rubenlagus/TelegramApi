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
public class TLKeyboardButtonRequestGeoLocation extends TLAbsKeyboardButton {
    public static final int CLASS_ID = 0xfc796b3f;

    public TLKeyboardButtonRequestGeoLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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
        return "keyboardButtonRequestGeoLocation#fc796b3f";
    }
}
