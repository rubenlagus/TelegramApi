package org.telegram.api.input.sticker.set;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of July of 2015
 */
public class TLInputStickerSetShortName extends TLAbsInputStickerSet {
    public static final int CLASS_ID = 0x861cc8a0;

    private String shortName;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.shortName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.shortName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "sticker.set.inputStickerSetShortName#861cc8a0";
    }
}
