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

package org.telegram.api.input.media;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media document.
 */
public class TLInputMediaGifExternal extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4843b0fd;

    /**
     * The Id.
     */
    protected String url;
    private String q;

    /**
     * Instantiates a new TL input media document.
     */
    public TLInputMediaGifExternal() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLString(this.q, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.q = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaGifExternal#4843b0fd";
    }
}