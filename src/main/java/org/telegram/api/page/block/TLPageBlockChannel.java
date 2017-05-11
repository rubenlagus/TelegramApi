package org.telegram.api.page.block;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockChannel extends TLAbsPageBlock {
    public static final int CLASS_ID = 0xef1751b5;

    private TLAbsChat channel;

    public TLPageBlockChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChat getChannel() {
        return channel;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(channel, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        channel = StreamingUtils.readTLObject(stream, context, TLAbsChat.class);
    }

    @Override
    public String toString() {
        return "pageBlockChannel#ef1751b5";
    }
}
