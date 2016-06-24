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

import org.telegram.api.bot.inlineresult.TLAbsBotInlineResult;
import org.telegram.api.input.geopoint.TLAbsInputGeoPoint;
import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesGetInlineBotResults extends TLMethod<TLAbsBotInlineResult> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x514e999d;

    private static final int FLAG_GEO_POINT = 0x00000001; // 0

    private int flags;
    private TLAbsInputUser bot;
    private TLAbsInputPeer peer;
    private TLAbsInputGeoPoint geoPoint;
    private String query;
    private String offset;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesGetInlineBotResults() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getBot() {
        return bot;
    }

    public String getQuery() {
        return query;
    }

    public String getOffset() {
        return offset;
    }

    public void setBot(TLAbsInputUser bot) {
        this.bot = bot;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public TLAbsBotInlineResult deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsBotInlineResult)) {
            return (TLAbsBotInlineResult) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsBotInlineResult.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(this.bot, stream);
        StreamingUtils.writeTLObject(peer, stream);
        if ((flags & FLAG_GEO_POINT) != 0) {
            StreamingUtils.writeTLObject(geoPoint, stream);
        }
        StreamingUtils.writeTLString(this.query, stream);
        StreamingUtils.writeTLString(this.offset, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        bot = (TLAbsInputUser) StreamingUtils.readTLObject(stream, context);
        peer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        if ((flags & FLAG_GEO_POINT) != 0) {
            geoPoint = (TLAbsInputGeoPoint) StreamingUtils.readTLObject(stream, context);
        }
        query = StreamingUtils.readTLString(stream);
        offset = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messages.getInlineBotResults#514e999d";
    }
}