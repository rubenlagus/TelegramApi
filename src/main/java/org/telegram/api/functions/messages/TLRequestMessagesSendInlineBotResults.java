/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesSendInlineBotResults extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb16e06fe;

    private static final int FLAG_REPLY       = 0x00000001; // 0
    private static final int FLAG_UNUSED1     = 0x00000002; // 1
    private static final int FLAG_UNUSED2     = 0x00000004; // 2
    private static final int FLAG_UNUSED3     = 0x00000008; // 3
    private static final int FLAG_BROADCAST   = 0x00000010; // 4
    private static final int FLAG_SILENT           = 0x00000020; // 5
    private static final int FLAG_BACKGROUND       = 0x00000040; // 6

    private int flags;
    private TLAbsInputPeer peer;
    private int replyToMsgId;
    private long randomId;
    private long queryId;
    private String id;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesSendInlineBotResults() {
        super();
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
    }

    public long getRandomId() {
        return randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        if ((flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeTLString(id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        if ((flags & FLAG_REPLY) != 0) {
            replyToMsgId = StreamingUtils.readInt(stream);
        }
        randomId = StreamingUtils.readLong(stream);
        queryId = StreamingUtils.readLong(stream);
        id = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messages.sendInlineBotResult#b16e06fe";
    }
}