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
package org.telegram.api.messages;

import org.telegram.api.bot.TLInlineBotSwitchPm;
import org.telegram.api.bot.inlineresult.TLAbsBotInlineResult;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLBotResults extends TLObject {
    public static final int CLASS_ID = 0xccd3563d;

    private static final int FLAG_GALLERY     = 0x00000001; // 0
    private static final int FLAG_NEXT_OFFSET = 0x00000002; // 1
    private static final int FLAG_SWITCH_PM   = 0x00000004; // 2

    private int flags;
    private long queryId;
    private String nextOffset;
    private TLVector<TLAbsBotInlineResult> results;
    private TLInlineBotSwitchPm switchPm;
    private int cacheTime;

    public TLBotResults() {
        super();
    }

    public long getQueryId() {
        return queryId;
    }

    public String getNextOffset() {
        return nextOffset;
    }

    public TLVector<TLAbsBotInlineResult> getResults() {
        return results;
    }

    public int getFlags() {
        return flags;
    }

    public TLInlineBotSwitchPm getSwitchPm() {
        return switchPm;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public boolean isGallery() {
        return (flags & FLAG_GALLERY) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(queryId, stream);
        if ((flags & FLAG_NEXT_OFFSET) != 0) {
            StreamingUtils.writeTLString(nextOffset, stream);
        }
        if ((flags & FLAG_SWITCH_PM) != 0) {
            StreamingUtils.writeTLObject(switchPm, stream);
        }
        StreamingUtils.writeTLVector(results, stream);
        StreamingUtils.writeInt(cacheTime, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        if ((flags & FLAG_NEXT_OFFSET) != 0) {
            nextOffset = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_SWITCH_PM) != 0) {
            switchPm = StreamingUtils.readTLObject(stream, context, TLInlineBotSwitchPm.class);
        }
        results = StreamingUtils.readTLVector(stream, context, TLAbsBotInlineResult.class);
        cacheTime = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.botResults#ccd3563d";
    }
}
