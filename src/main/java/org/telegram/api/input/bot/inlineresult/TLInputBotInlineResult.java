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
public class TLInputBotInlineResult extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0x2cbbe15a;

    private static final int FLAG_UNUSED0         = 0x00000001; // 0
    private static final int FLAG_TITLE           = 0x00000002; // 1
    private static final int FLAG_DESCRIPTION     = 0x00000004; // 2
    private static final int FLAG_URL             = 0x00000008; // 3
    private static final int FLAG_THUMB_URL       = 0x00000010; // 4
    private static final int FLAG_CONTENT         = 0x00000020; // 5
    private static final int FLAG_SIZE            = 0x00000040; // 6
    private static final int FLAG_DURATION        = 0x00000080; // 7

    private int flags;
    private String id;
    private String type;
    private String title;
    private String description;
    private String url;
    private String thumbUrl;
    private String contentUrl;
    private String contentType;
    private int w;
    private int h;
    private int duration;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResult() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(TLAbsInputBotInlineMessage sendMessage) {
        this.sendMessage = sendMessage;
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
        if ((flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(url, stream);
        }
        if ((flags & FLAG_THUMB_URL) != 0) {
            StreamingUtils.writeTLString(thumbUrl, stream);
        }
        if ((flags & FLAG_CONTENT) != 0) {
            StreamingUtils.writeTLString(contentUrl, stream);
            StreamingUtils.writeTLString(contentType, stream);
        }
        if ((flags & FLAG_SIZE) != 0) {
            StreamingUtils.writeInt(w, stream);
            StreamingUtils.writeInt(h, stream);
        }
        if ((flags & FLAG_DURATION) != 0) {
            StreamingUtils.writeInt(duration, stream);
        }
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
        if ((flags & FLAG_URL) != 0) {
            this.url = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_THUMB_URL) != 0) {
            this.thumbUrl = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_CONTENT) != 0) {
            this.contentUrl = StreamingUtils.readTLString(stream);
            this.contentType = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_SIZE) != 0) {
            this.w = StreamingUtils.readInt(stream);
            this.h = StreamingUtils.readInt(stream);
        }
        if ((flags & FLAG_DURATION) != 0) {
            this.duration = StreamingUtils.readInt(stream);
        }
        this.sendMessage = (TLAbsInputBotInlineMessage) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "inputBotInlineResult#2cbbe15a";
    }
}
