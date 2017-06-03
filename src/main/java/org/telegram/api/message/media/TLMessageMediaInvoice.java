package org.telegram.api.message.media;

import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.paymentapi.TLWebDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLMessageMediaInvoice extends TLAbsMessageMedia {
    public static final int CLASS_ID = 0x84551347;

    private static final int FLAG_PHOTO                      = 0x00000001; // 0
    private static final int FLAG_SHIPPING_ADDRESS_REQUEST   = 0x00000002; // 1
    private static final int FLAG_RECEIPT_MSG_ID             = 0x00000004; // 2
    private static final int FLAG_TEST                       = 0x00000008; // 3

    private int flags;
    private String title;
    private String description;
    private TLWebDocument photo;
    private int receiptMsgId;
    private String currency;
    private Long totalAmount;
    private String startParam;

    public TLMessageMediaInvoice() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLWebDocument getPhoto() {
        return photo;
    }

    public boolean hasPhoto() {
        return (flags & FLAG_PHOTO) != 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReceiptMsgId() {
        return receiptMsgId;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public String getStartParam() {
        return startParam;
    }


    public boolean hasReceiptMsgId() {
        return (flags & FLAG_RECEIPT_MSG_ID) != 0;
    }


    public boolean isTest() {
        return (flags & FLAG_TEST) != 0;
    }


    public boolean hasShippingAddressRequested() {
        return (flags & FLAG_SHIPPING_ADDRESS_REQUEST) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLString(title, stream);
        StreamingUtils.writeTLString(description, stream);
        if ((flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(photo, stream);
        }
        if ((flags & FLAG_RECEIPT_MSG_ID) != 0) {
            StreamingUtils.writeInt(receiptMsgId, stream);
        }
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeLong(totalAmount, stream);
        StreamingUtils.writeTLString(startParam, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        title = StreamingUtils.readTLString(stream);
        description = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_PHOTO) != 0) {
            photo = StreamingUtils.readTLObject(stream, context, TLWebDocument.class);
        }
        if ((flags & FLAG_RECEIPT_MSG_ID) != 0) {
            receiptMsgId = StreamingUtils.readInt(stream);
        }
        currency = StreamingUtils.readTLString(stream);
        totalAmount = StreamingUtils.readLong(stream);
        startParam = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messageMediaInvoice#84551347";
    }
}