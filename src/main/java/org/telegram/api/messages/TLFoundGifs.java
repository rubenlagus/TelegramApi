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
package org.telegram.api.messages;

import org.telegram.api.foundgif.TLAbsFoundGif;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected messages.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAffectedMessages
 * @date 11 of April of 2015
 */
public class TLFoundGifs extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x450a1c0a;

    private int nextOffset;
    private TLVector<TLAbsFoundGif> results;

    /**
     * Instantiates a new TL affected messages.
     */
    public TLFoundGifs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(int nextOffset) {
        this.nextOffset = nextOffset;
    }

    public TLVector<TLAbsFoundGif> getResults() {
        return results;
    }

    public void setResults(TLVector<TLAbsFoundGif> results) {
        this.results = results;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.nextOffset, stream);
        StreamingUtils.writeTLVector(this.results, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.nextOffset = StreamingUtils.readInt(stream);
        this.results = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.foundGifs#450a1c0a";
    }
}
