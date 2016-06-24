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
import org.telegram.api.input.photo.TLAbsInputPhoto;
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
public class TLInputBotInlineResultPhoto extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0xa8d864a7;

    private String id;
    private String type;
    private TLAbsInputPhoto photo;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResultPhoto() {
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

    public TLAbsInputPhoto getPhoto() {
        return photo;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(type, stream);
        StreamingUtils.writeTLObject(photo, stream);
        StreamingUtils.writeTLObject(sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        type = StreamingUtils.readTLString(stream);
        photo = (TLAbsInputPhoto) StreamingUtils.readTLObject(stream, context);
        sendMessage = (TLAbsInputBotInlineMessage) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "inputBotInlineResultPhoto#a8d864a7";
    }
}
