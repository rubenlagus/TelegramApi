package org.telegram.api.keyboard.replymarkup;

import org.telegram.api.keyboard.TLKeyboardButtonRow;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
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
public class TLReplayInlineKeyboardMarkup extends TLAbsReplyMarkup {
    public static final int CLASS_ID = 0x48a30254;

    private TLVector<TLKeyboardButtonRow> rows = new TLVector<>();

    public TLReplayInlineKeyboardMarkup() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLKeyboardButtonRow> getRows() {
        return rows;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.rows, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.rows = (TLVector<TLKeyboardButtonRow>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "replyInlineMarkup#48a30254";
    }
}
