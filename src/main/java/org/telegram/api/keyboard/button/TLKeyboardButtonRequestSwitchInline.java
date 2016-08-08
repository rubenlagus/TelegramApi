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
    public static final int CLASS_ID = 0x568a748;

    private static final int FLAG_SAME_PEER   = 0x00000001; // 0

    private int flags;
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

    public boolean toSamePeer() {
        return (flags & FLAG_SAME_PEER) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(text, stream);
        StreamingUtils.writeTLString(query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.text = StreamingUtils.readTLString(stream);
        this.query = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonSwitchInline#568a748";
    }
}
