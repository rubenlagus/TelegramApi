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

package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message action empty.
 */
public class TLMessageActionChannelMigratedFrom extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb055eaee;

    private String title;
    private int chatId;

    /**
     * Instantiates a new TL message action empty.
     */
    public TLMessageActionChannelMigratedFrom() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(title, stream);
        StreamingUtils.writeInt(chatId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLString(stream);
        this.chatId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionChannelMigratedFrom#b055eaee";
    }
}