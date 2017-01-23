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
    public static final int CLASS_ID = 0x36585ea4;

    private static final int FLAG_MESSAGE   = 0x00000001; // 0
    private static final int FLAG_ALERT     = 0x00000002; // 1
    private static final int FLAG_URL       = 0x00000004; // 2
    private static final int FLAG_HAS_URL   = 0x00000008; // 3

    private int flags;
    private String message;
    private String url;
    private int cacheTime;

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

    public String getUrl() {
        return url;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public boolean hasAlert() {
        return (flags & FLAG_ALERT) != 0;
    }

    public boolean hasMessage() {
        return (flags & FLAG_MESSAGE) != 0;
    }

    public boolean hasUrl() {
        return (flags & FLAG_HAS_URL) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeTLString(message, stream);
        }
        if ((flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(url, stream);
        }
        StreamingUtils.writeInt(cacheTime, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_MESSAGE) != 0) {
            message = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_URL) != 0) {
            url = StreamingUtils.readTLString(stream);
        }
        cacheTime = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.botCallbackAnswer#36585ea4";
    }
}