package org.telegram.api.user;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user empty.
 */
public class TLUserEmpty extends TLAbsUser {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x200250ba;

    /**
     * Instantiates a new TL user empty.
     */
    public TLUserEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "userEmpty#200250ba";
    }
}