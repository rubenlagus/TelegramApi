package org.telegram.api.messages;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages.
 */
public class TLMessagesBotCallbackAnswer extends TLObject {
    public static final int CLASS_ID = 0x1264f1c6;

    private static final int FLAG_MESSAGE = 0x00000000; // 0
    private static final int FLAG_ALERT   = 0x00000001; // 1


    private int flags;
    private String message;

    public TLMessagesBotCallbackAnswer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasAlert() {
        return (flags & FLAG_ALERT) != 0;
    }

    public boolean hasMessage() {
        return (flags & FLAG_MESSAGE) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeTLString(message, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            message = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "messages.botCallbackAnswer#1264f1c6";
    }
}