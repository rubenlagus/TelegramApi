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

import org.telegram.api.input.bot.TLInputBotInlineMessageId;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateInlineBotCallbackQuery extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2cbd95af;

    private long queryId;
    private int userId;
    private TLInputBotInlineMessageId msgId;
    private TLBytes data;

    /**
     * Instantiates a new TL update channel group
     */
    public TLUpdateInlineBotCallbackQuery() {
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

    public TLInputBotInlineMessageId getMsgId() {
        return msgId;
    }

    public TLBytes getData() {
        return data;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(queryId, stream);
        StreamingUtils.writeInt(userId, stream);
        StreamingUtils.writeTLObject(msgId, stream);
        StreamingUtils.writeTLBytes(data, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        queryId = StreamingUtils.readLong(stream);
        userId = StreamingUtils.readInt(stream);
        msgId = (TLInputBotInlineMessageId) StreamingUtils.readTLObject(stream, context);
        data = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "updateInlineBotCallbackQuery#2cbd95af";
    }
}