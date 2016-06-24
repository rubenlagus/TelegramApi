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

package org.telegram.api.update;

import org.telegram.api.message.TLAbsMessage;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateEditMessage extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe40370a3;

    private TLAbsMessage message;
    private int pts;
    private int ptsCount;

    /**
     * Instantiates a new TL update channel group
     */
    public TLUpdateEditMessage() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessage getMessage() {
        return message;
    }

    @Override
    public int getPts() {
        return pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(message, stream);
        StreamingUtils.writeInt(pts, stream);
        StreamingUtils.writeInt(ptsCount, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        message = (TLAbsMessage) StreamingUtils.readTLObject(stream, context);
        pts = StreamingUtils.readInt(stream);
        ptsCount = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateEditMessage#e40370a3";
    }
}