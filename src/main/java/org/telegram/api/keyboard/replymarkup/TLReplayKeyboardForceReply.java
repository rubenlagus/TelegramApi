package org.telegram.api.keyboard.replymarkup;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Hide custom keyboard
 * @date 07 of July of 2015
 */
public class TLReplayKeyboardForceReply extends TLAbsReplyMarkup {
    public static final int CLASS_ID = 0xf4108aa0;

    private static final int FLAG_UNUSED0 = 0x00000001; // 0
    private static final int FLAG_SINGLEUSE = 0x00000002; // 1
    private static final int FLAG_SELECTIVE = 0x00000004; // 2

    protected int flags;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "keyboard#ReplyKeyboardForceReply#f4108aa0";
    }
}
