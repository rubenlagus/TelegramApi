package org.telegram.api.input.media;

import org.telegram.api.input.file.TLAbsInputFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaDocumentExternal extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7477f92c;

    private TLAbsInputFile url;

    /**
     * Instantiates a new TL input media photo.
     */
    public TLInputMediaDocumentExternal() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputFile getUrl() {
        return url;
    }

    public void setUrl(TLAbsInputFile url) {
        this.url = url;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.url, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.url = (TLAbsInputFile) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "inputMediaDocumentExternal#7477f92c";
    }
}