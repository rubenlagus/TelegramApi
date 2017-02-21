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

import org.telegram.api.TLDataJSON;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLUpdateBotWebhookJSONQuery extends TLAbsUpdate {
    public static final int CLASS_ID = 0x9b9240a6;

    private long queryId;
    private TLDataJSON data;
    private int timeout;

    public TLUpdateBotWebhookJSONQuery() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLDataJSON getData() {
        return data;
    }

    public long getQueryId() {
        return queryId;
    }

    public int getTimeout() {
        return timeout;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(queryId, stream);
        StreamingUtils.writeTLObject(data, stream);
        StreamingUtils.writeInt(timeout, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        queryId = StreamingUtils.readLong(stream);
        data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
        timeout = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateBotWebhookJSONQuery#9b9240a6";
    }
}