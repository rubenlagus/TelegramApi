package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get stickers.
 */
public class TLRequestMessagesStartBot extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6df7378;

    private TLAbsInputUser bot;
    private TLAbsInputPeer peer;
    private long randomId;
    private String startParam;

    /**
     * Instantiates a new TL request messages get stickers.
     */
    public TLRequestMessagesStartBot() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputUser getBot() {
        return bot;
    }

    public void setBot(TLAbsInputUser bot) {
        this.bot = bot;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public long getRandomId() {
        return randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    public String getStartParam() {
        return startParam;
    }

    public void setStartParam(String startParam) {
        this.startParam = startParam;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.bot, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLString(this.startParam, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.bot = (TLAbsInputUser) StreamingUtils.readTLObject(stream, context);
        this.peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.randomId = StreamingUtils.readLong(stream);
        this.startParam = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "bot.startBot#e6df7378";
    }
}