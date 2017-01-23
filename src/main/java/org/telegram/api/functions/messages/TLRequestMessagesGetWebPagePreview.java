package org.telegram.api.functions.messages;

import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get web page preview.
 */
public class TLRequestMessagesGetWebPagePreview extends TLMethod<TLAbsMessageMedia> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x25223e24;

    private String message = "";

    /**
     * Instantiates a new TL request messages get web page preview.
     */
    public TLRequestMessagesGetWebPagePreview() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public TLAbsMessageMedia deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsMessageMedia))
            return (TLAbsMessageMedia) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.message.media.TLAbsMessageMedia, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.message = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "messages.getWebPagePreview#25223e24";
    }
}