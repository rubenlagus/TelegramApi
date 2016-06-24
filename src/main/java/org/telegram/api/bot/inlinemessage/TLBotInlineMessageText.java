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

import org.telegram.api.keyboard.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 13 of February of 2016
 */
public class TLBotInlineMessageText extends TLAbsBotInlineMessage {
    public static final int CLASS_ID = 0x8c7f65e2;

    private static final int FLAG_NO_WEBPAGE    = 0x00000001; // 0
    private static final int FLAG_ENTITIES      = 0x00000002; // 1
    private static final int FLAG_REPLYMARKUP   = 0x00000004; // 2

    private int flags;
    private String message;
    private TLVector<TLAbsMessageEntity> entites;
    private TLAbsReplyMarkup replyMarkup;

    public TLBotInlineMessageText() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public String getMessage() {
        return message;
    }

    public TLVector<TLAbsMessageEntity> getEntites() {
        return entites;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public boolean hasWebPreview() {
        return (flags & FLAG_NO_WEBPAGE) == 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(message, stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(entites, stream);
        }
        if ((flags & FLAG_REPLYMARKUP) != 0) {
            StreamingUtils.writeTLObject(replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            this.entites = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }
        if ((flags & FLAG_REPLYMARKUP) != 0) {
            replyMarkup = (TLAbsReplyMarkup) StreamingUtils.readTLObject(stream, context);
        }
    }

    @Override
    public String toString() {
        return "botInlineMessageText#8c7f65e2";
    }
}
