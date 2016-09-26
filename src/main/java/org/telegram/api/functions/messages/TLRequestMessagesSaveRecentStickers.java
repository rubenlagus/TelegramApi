package org.telegram.api.functions.messages;

import org.telegram.api.input.document.TLAbsInputDocument;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesSaveRecentStickers extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x392718f8;

    private static final int FLAG_ATTACHED = 0x00000001; // 0

    private int flags;
    private TLAbsInputDocument id;
    private boolean unsave;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesSaveRecentStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLBool)) {
            return (TLBool) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputDocument getId() {
        return id;
    }

    public void setId(TLAbsInputDocument id) {
        this.id = id;
    }

    public boolean isUnsave() {
        return unsave;
    }

    public void setUnsave(boolean unsave) {
        this.unsave = unsave;
    }

    public void enableAttached(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_ATTACHED;
        } else {
            this.flags &= ~FLAG_ATTACHED;
        }
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(id, stream);
        StreamingUtils.writeTLBool(unsave, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        unsave = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "messages.saveRecentSticker#392718f8";
    }
}