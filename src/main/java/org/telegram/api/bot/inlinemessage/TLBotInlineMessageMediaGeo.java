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
package org.telegram.api.bot.inlinemessage;

import org.telegram.api.geo.point.TLAbsGeoPoint;
import org.telegram.api.keyboard.replymarkup.TLAbsReplyMarkup;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 13 of February of 2016
 */
public class TLBotInlineMessageMediaGeo extends TLAbsBotInlineMessage {
    public static final int CLASS_ID = 0x3a8fd8b8;

    private static final int FLAG_UNUSED0        = 0x00000001; // 0
    private static final int FLAG_UNUSED1        = 0x00000002; // 1
    private static final int FLAG_REPLY_MARKUP   = 0x00000004; // 2

    private int flags;
    private TLAbsGeoPoint geoPoint;
    private TLAbsReplyMarkup replyMarkup;

    public TLBotInlineMessageMediaGeo() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public TLAbsGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(geoPoint, stream);
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        geoPoint = (TLAbsGeoPoint) StreamingUtils.readTLObject(stream, context);
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
    }

    @Override
    public String toString() {
        return "botInlineMessageMediaGeo#3a8fd8b8";
    }
}
