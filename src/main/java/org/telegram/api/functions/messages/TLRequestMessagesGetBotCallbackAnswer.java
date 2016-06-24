package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.TLMessagesBotCallbackAnswer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit admin
 */
public class TLRequestMessagesGetBotCallbackAnswer extends TLMethod<TLMessagesBotCallbackAnswer> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x130c2c85;

    private TLAbsInputPeer peer;
    private int msgId;
    private TLBytes data;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestMessagesGetBotCallbackAnswer() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesBotCallbackAnswer deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesBotCallbackAnswer)) {
            return (TLMessagesBotCallbackAnswer) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesBotCallbackAnswer.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public int getMsgId() {
        return msgId;
    }

    public TLBytes getData() {
        return data;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(msgId, stream);
        StreamingUtils.writeTLBytes(data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        msgId = StreamingUtils.readInt(stream);
        data = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "messages.getBotCallbackAnswer#a6e94f04";
    }
}