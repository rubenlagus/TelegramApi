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

package org.telegram.api.update;

import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateBotCallbackQuery extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe73547e1;

    private static final int FLAG_DATA    = 0x00000001; // 0
    private static final int FLAG_GAMEID  = 0x00000002; // 1

    private int flags;
    private long queryId;
    private int userId;
    private TLAbsPeer peer;
    private int msgId;
    private long chatInstance;
    private TLBytes data;
    private String gameShortName;

    /**
     * Instantiates a new TL update channel group
     */
    public TLUpdateBotCallbackQuery() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public long getQueryId() {
        return queryId;
    }

    public int getUserId() {
        return userId;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public int getMsgId() {
        return msgId;
    }

    public TLBytes getData() {
        return data;
    }

    public long getChatInstance() {
        return chatInstance;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(msgId, stream);
        StreamingUtils.writeLong(chatInstance, stream);
        if ((flags & FLAG_DATA) != 0) {
            StreamingUtils.writeTLBytes(data, stream);
        }
        if ((flags & FLAG_GAMEID) != 0) {
            StreamingUtils.writeTLString(gameShortName, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.queryId = StreamingUtils.readLong(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
        this.msgId = StreamingUtils.readInt(stream);
        this.chatInstance = StreamingUtils.readLong(stream);
        if ((flags & FLAG_DATA) != 0) {
            data = StreamingUtils.readTLBytes(stream, context);
        }
        if ((flags & FLAG_GAMEID) != 0) {
            gameShortName = StreamingUtils.readTLString(stream);
        }
    }

    public String toString() {
        return "updateBotCallbackQuery#e73547e1";
    }
}