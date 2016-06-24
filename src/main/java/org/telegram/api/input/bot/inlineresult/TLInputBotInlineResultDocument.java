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
package org.telegram.api.input.bot.inlineresult;

import org.telegram.api.input.bot.inlinemessage.TLAbsInputBotInlineMessage;
import org.telegram.api.input.document.TLAbsInputDocument;
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
public class TLInputBotInlineResultDocument extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0xfff8fdc4;

    private static final int FLAG_UNUSED0         = 0x00000001; // 0
    private static final int FLAG_TITLE           = 0x00000002; // 1
    private static final int FLAG_DESCRIPTION     = 0x00000004; // 2
    private static final int FLAG_UNUSED3         = 0x00000008; // 3
    private static final int FLAG_UNUSED4         = 0x00000010; // 4
    private static final int FLAG_UNUSED5         = 0x00000020; // 5
    private static final int FLAG_UNUSED6         = 0x00000040; // 6
    private static final int FLAG_UNUSED7         = 0x00000080; // 7

    private int flags;
    private String id;
    private String type;
    private String title;
    private String description;
    private TLAbsInputDocument document;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResultDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getFlags() {
        return flags;
    }

    public TLAbsInputDocument getDocument() {
        return document;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(type, stream);
        if ((flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(title, stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            StreamingUtils.writeTLString(description, stream);
        }
        StreamingUtils.writeTLObject(document, stream);
        StreamingUtils.writeTLObject(sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readTLString(stream);
        type = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_TITLE) != 0) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            this.description = StreamingUtils.readTLString(stream);
        }
        document = (TLAbsInputDocument) StreamingUtils.readTLObject(stream, context);
        sendMessage = (TLAbsInputBotInlineMessage) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "inputBotInlineResultDocument#fff8fdc4";
    }
}
