package org.telegram.api.dialog;

import org.telegram.api.peer.TLAbsPeer;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dialog.
 */
public class TLDialog extends TLAbsDialog {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc1dd804a;

    /**
     * Instantiates a new TL dialog.
     */
    public TLDialog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.topMessage, stream);
        StreamingUtils.writeInt(this.readInboxMaxId, stream);
        StreamingUtils.writeInt(this.unreadCount, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsPeer) StreamingUtils.readTLObject(stream, context));
        this.topMessage = StreamingUtils.readInt(stream);
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.notifySettings = (TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "dialog.TLDialog#c1dd804a";
    }
}