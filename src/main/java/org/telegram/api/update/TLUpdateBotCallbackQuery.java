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
    public static final int CLASS_ID = 0xa68c688c;

    private long queryId;
    private int userId;
    private TLAbsPeer peer;
    private int msgId;
    private TLBytes data;

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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(msgId, stream);
        StreamingUtils.writeTLBytes(data, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.queryId = StreamingUtils.readLong(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
        this.msgId = StreamingUtils.readInt(stream);
        data = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "updateBotCallbackQuery#a68c688c";
    }
}