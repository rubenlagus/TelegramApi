package org.telegram.api.paymentapi;

import org.telegram.api.document.attribute.TLAbsDocumentAttribute;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLWebDocument extends TLObject {
    public static final int CLASS_ID = 0xc61acbd8;

    private String url;
    private long accessHash;
    private int size;
    private String mimeType;
    private TLVector<TLAbsDocumentAttribute> attributes;
    private int dcId;

    public TLWebDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public int getSize() {
        return size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return attributes;
    }

    public int getDcId() {
        return dcId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(size, stream);
        StreamingUtils.writeTLString(mimeType, stream);
        StreamingUtils.writeTLVector(attributes, stream);
        StreamingUtils.writeInt(dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        url = StreamingUtils.readTLString(stream);
        accessHash = StreamingUtils.readLong(stream);
        size = StreamingUtils.readInt(stream);
        mimeType = StreamingUtils.readTLString(stream);
        attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
        dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "webDocument#c61acbd8";
    }
}
