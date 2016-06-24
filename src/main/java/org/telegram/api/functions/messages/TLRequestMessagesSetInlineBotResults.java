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

import org.telegram.api.bot.TLInlineBotSwitchPm;
import org.telegram.api.input.bot.inlineresult.TLAbsInputBotInlineResult;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesSetInlineBotResults extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeb5ea206;

    private static final int FLAG_GALLERY     = 0x00000001; // 0
    private static final int FLAG_PRIVATE     = 0x00000002; // 1
    private static final int FLAG_NEXT_OFFSET = 0x00000004; // 2
    private static final int FLAG_SWITCH_PM   = 0x00000008; // 3

    private int flags;
    private long queryId;
    private TLVector<TLAbsInputBotInlineResult> results;
    private int cacheTime;
    private String nextOffset;
    private TLInlineBotSwitchPm switchPm;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesSetInlineBotResults() {
        super();
    }

    public long getQueryId() {
        return queryId;
    }

    public TLVector<TLAbsInputBotInlineResult> getResults() {
        return results;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public String getNextOffset() {
        return nextOffset;
    }

    public TLInlineBotSwitchPm getSwitchPm() {
        return switchPm;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeTLVector(this.results, stream);
        StreamingUtils.writeInt(this.cacheTime, stream);
        if ((flags & FLAG_NEXT_OFFSET) != 0) {
            StreamingUtils.writeTLString(this.nextOffset, stream);
        }
        if ((flags & FLAG_SWITCH_PM) != 0) {
            StreamingUtils.writeTLObject(switchPm, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        results = (TLVector<TLAbsInputBotInlineResult>) StreamingUtils.readTLVector(stream, context);
        cacheTime = StreamingUtils.readInt(stream);
        if ((flags & FLAG_NEXT_OFFSET) != 0) {
            nextOffset = StreamingUtils.readTLString(stream);
        }
        switchPm = (TLInlineBotSwitchPm) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "messages.setInlineBotResults#eb5ea206";
    }
}