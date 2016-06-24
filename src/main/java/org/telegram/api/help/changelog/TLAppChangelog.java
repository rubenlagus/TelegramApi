package org.telegram.api.help.changelog;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL app changelog.
 */
public class TLAppChangelog extends TLAbsAppChangelog {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4668e6bd;

    private String text;

    /**
     * Instantiates a new TL app changelog.
     */
    public TLAppChangelog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.text = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.AppChangelog#4668e6bd";
    }
}