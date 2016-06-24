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

import org.telegram.api.document.TLAbsDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesGetDocumentByHash extends TLMethod<TLAbsDocument> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x338e2464;

    private TLBytes sha256;
    private int size;
    private String mimeType;

    /**
     * Instantiates a new TL request messages create chat.
     */
    public TLRequestMessagesGetDocumentByHash() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsDocument deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsDocument)) {
            return (TLAbsDocument) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsDocument.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLBytes getSha256() {
        return sha256;
    }

    public void setSha256(TLBytes sha256) {
        this.sha256 = sha256;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(sha256, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.sha256 = StreamingUtils.readTLBytes(stream, context);
        this.size = StreamingUtils.readInt(stream);
        mimeType = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messages.getDocumentByHash#338e2464";
    }
}