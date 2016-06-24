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

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.messages.TLAbsMessages;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages search.
 */
public class TLRequestMessagesSearchGlobal extends TLMethod<TLAbsMessages> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9e3cacb0;

    private String q;
    private int offsetDate;
    private TLAbsInputPeer offsetPeer;
    private int offsetId;
    private int limit;

    /**
     * Instantiates a new TL request messages search.
     */
    public TLRequestMessagesSearchGlobal() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsMessages)) {
            return (TLAbsMessages) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsMessages.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getOffsetDate() {
        return offsetDate;
    }

    public void setOffsetDate(int offsetDate) {
        this.offsetDate = offsetDate;
    }

    public TLAbsInputPeer getOffsetPeer() {
        return offsetPeer;
    }

    public void setOffsetPeer(TLAbsInputPeer offsetPeer) {
        this.offsetPeer = offsetPeer;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.offsetDate, stream);
        StreamingUtils.writeTLObject(this.offsetPeer, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.q = StreamingUtils.readTLString(stream);
        this.offsetDate = StreamingUtils.readInt(stream);
        this.offsetPeer = (TLAbsInputPeer) StreamingUtils.readTLObject(stream, context);
        this.offsetId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "messages.searchGlobal#9e3cacb0";
    }
}