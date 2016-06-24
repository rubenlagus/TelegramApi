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

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLLongVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new message.
 */
public class TLUpdateStickerSetsOrder extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf0dfb451;

    private TLLongVector order;

    /**
     * Instantiates a new TL update new message.
     */
    public TLUpdateStickerSetsOrder() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector getOrder() {
        return order;
    }

    public void setOrder(TLLongVector order) {
        this.order = order;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.order, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.order = StreamingUtils.readTLLongVector(stream, context);
    }

    public String toString() {
        return "updateStickerSetsOrder#f0dfb451";
    }
}