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
public class TLMessagesEditData extends TLObject {
    public static final int CLASS_ID = 0x26b5dde6;

    private static final int FLAG_CAPTION = 0x00000001; // 0

    private int flags;

    public TLMessagesEditData() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isCaptionEdited() {
        return (flags & FLAG_CAPTION) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.messageEditData#26b5dde6";
    }
}