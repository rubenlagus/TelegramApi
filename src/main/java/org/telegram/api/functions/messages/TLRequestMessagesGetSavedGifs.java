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

import org.telegram.api.messages.savedgifs.TLAbsSavedGifs;
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
public class TLRequestMessagesGetSavedGifs extends TLMethod<TLAbsSavedGifs> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x83bf3d52;

    private int hash;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesGetSavedGifs() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsSavedGifs deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsSavedGifs)) {
            return (TLAbsSavedGifs) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsSavedGifs.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        hash = StreamingUtils.readInt(stream);

    }

    public String toString() {
        return "messages.getSavedGifs#83bf3d52";
    }
}