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

package org.telegram.api.input.messages.filter;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLMessagesFilterPhoneCalls extends TLAbsMessagesFilter {
    public static final int CLASS_ID = 0x80c99768;

    private static final int FLAG_MISSED        = 0x00000001; // 0

    private int flags;

    public TLMessagesFilterPhoneCalls() {
        super();
    }

    private void includeMissed(boolean included) {
        if (included) {
            this.flags |= FLAG_MISSED;
        } else {
            this.flags &= ~FLAG_MISSED;
        }
    }

    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "inputMessagesFilterPhoneCalls#80c99768";
    }
}