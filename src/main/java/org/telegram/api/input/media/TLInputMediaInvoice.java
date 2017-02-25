package org.telegram.api.input.media;

import org.telegram.api.input.paymentapi.TLInputWebDocument;
import org.telegram.api.paymentapi.TLInvoice;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLInputMediaInvoice extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x92153685;

    private static final int FLAG_PHOTO = 0x00000001; // 0

    private int flags;
    private String title;
    private String description;
    private TLInputWebDocument photo;
    private TLInvoice invoice;
    private TLBytes payload;
    private String provider;
    private String startParam;

    public TLInputMediaInvoice() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TLInputWebDocument getPhoto() {
        return photo;
    }

    public void setPhoto(TLInputWebDocument photo) {
        if (photo == null) {
            flags &= ~FLAG_PHOTO;
        } else {
            flags |= FLAG_PHOTO;
        }
        this.photo = photo;
    }

    public TLInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(TLInvoice invoice) {
        this.invoice = invoice;
    }

    public TLBytes getPayload() {
        return payload;
    }

    public void setPayload(TLBytes payload) {
        this.payload = payload;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStartParam() {
        return startParam;
    }

    public void setStartParam(String startParam) {
        this.startParam = startParam;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(title, stream);
        StreamingUtils.writeTLString(description, stream);
        if ((flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(photo, stream);
        }
        StreamingUtils.writeTLObject(invoice, stream);
        StreamingUtils.writeTLBytes(payload, stream);
        StreamingUtils.writeTLString(provider, stream);
        StreamingUtils.writeTLString(startParam, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        title = StreamingUtils.readTLString(stream);
        description = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_PHOTO) != 0) {
            photo = StreamingUtils.readTLObject(stream, context, TLInputWebDocument.class);
        }
        invoice = StreamingUtils.readTLObject(stream, context, TLInvoice.class);
        payload = StreamingUtils.readTLBytes(stream, context);
        provider = StreamingUtils.readTLString(stream);
        startParam = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaInvoice#92153685";
    }
}