package org.telegram.api.input.paymentapi;

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
public class TLInputWebDocument extends TLObject {
    public static final int CLASS_ID = 0x9bed434d;

    private String url;
    private int size;
    private String mimeType;
    private TLVector<TLAbsDocumentAttribute> attributes;


    public TLInputWebDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeInt(size, stream);
        StreamingUtils.writeTLString(mimeType, stream);
        StreamingUtils.writeTLVector(attributes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        url = StreamingUtils.readTLString(stream);
        size = StreamingUtils.readInt(stream);
        mimeType = StreamingUtils.readTLString(stream);
        attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
    }

    @Override
    public String toString() {
        return "inputWebDocument#9bed434d";
    }
}
