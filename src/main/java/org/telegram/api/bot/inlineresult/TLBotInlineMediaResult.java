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
package org.telegram.api.bot.inlineresult;

import org.telegram.api.bot.inlinemessage.TLAbsBotInlineMessage;
import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.photo.TLAbsPhoto;
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
public class TLBotInlineMediaResult extends TLAbsBotInlineResult {
    public static final int CLASS_ID = 0x17db940b;

    private static final int FLAG_PHOTO       = 0x00000001; // 0
    private static final int FLAG_DOCUMENT    = 0x00000002; // 1
    private static final int FLAG_TITLE       = 0x00000004; // 2
    private static final int FLAG_DESCRIPTION = 0x00000008; // 3

    private int flags;
    private String id;
    private String type;
    private TLAbsPhoto photo;
    private TLAbsDocument document;
    private String title;
    private String description;
    private TLAbsBotInlineMessage sendMessage;

    public TLBotInlineMediaResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public TLAbsBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    public int getFlags() {
        return flags;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(type, stream);
        if ((flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(photo, stream);
        }
        if ((flags & FLAG_DOCUMENT) != 0) {
            StreamingUtils.writeTLObject(photo, stream);
        }
        if ((flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(title, stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            StreamingUtils.writeTLString(title, stream);
        }
        StreamingUtils.writeTLObject(sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readTLString(stream);
        type = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_PHOTO) != 0) {
            photo = (TLAbsPhoto) StreamingUtils.readTLObject(stream, context);
        }
        if ((flags & FLAG_DOCUMENT) != 0) {
            document = (TLAbsDocument) StreamingUtils.readTLObject(stream, context);
        }
        if ((flags & FLAG_TITLE) != 0) {
            title = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            description = StreamingUtils.readTLString(stream);
        }
        this.sendMessage = (TLAbsBotInlineMessage) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "botInlineMediaResult#17db940b";
    }
}
