package org.telegram.api.keyboard;

import org.telegram.api.keyboard.button.TLAbsKeyboardButton;
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
 * @brief Hide custom keyboard
 * @date 07 of July of 2015
 */
public class TLKeyboardButtonRow extends TLObject {
    public static final int CLASS_ID = 0x77608b83;

    public TLVector<TLAbsKeyboardButton> buttons = new TLVector<>();

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.buttons, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.buttons = (TLVector<TLAbsKeyboardButton>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "keyboard.KeyboardButtonRow#77608b83";
    }
}
