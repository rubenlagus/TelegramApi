package org.telegram.api.help;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL app update.
 */
public class TLAppUpdate extends TLAbsAppUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8987f311;

    /**
     * Instantiates a new TL app update.
     */
    public TLAppUpdate() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLBool(this.critical, stream);
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.critical = StreamingUtils.readTLBool(stream);
        this.url = StreamingUtils.readTLString(stream);
        this.text = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.appUpdate#8987f311";
    }
}