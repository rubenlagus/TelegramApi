package org.telegram.api.input.media;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaPhotoExternal extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb55f4f18;

    private String url;
    private String caption;

    /**
     * Instantiates a new TL input media photo.
     */
    public TLInputMediaPhotoExternal() {
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLString(caption, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        caption = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputMediaPhotoExternal#b55f4f18";
    }
}