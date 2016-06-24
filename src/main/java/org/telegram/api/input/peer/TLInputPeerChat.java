package org.telegram.api.input.peer;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input peer chat.
 */
public class TLInputPeerChat extends TLAbsInputPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x179be863;

    private int chatId;

    /**
     * Instantiates a new TL input peer chat.
     */
    public TLInputPeerChat() {
        super();

    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return this.chatId;
    }

    /**
     * Sets chat id.
     *
     * @param chatId the chat id
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "inputPeerChat#179be863";
    }
}