package org.telegram.api.dialog;

import org.telegram.api.draft.TLAbsDraftMessage;
import org.telegram.api.peer.TLAbsPeer;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dialog.
 */
public class TLDialog extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x66ffba14;

    private static final int FLAG_PTS               = 0x00000001; // 0
    private static final int FLAG_DRAFT             = 0x00000002; // 1
    private static final int FLAG_PINNED            = 0x00000004; // 2

    private int flags;
    private TLAbsPeer peer;
    private int topMessage;
    private int readInboxMaxId;
    private int readOutboxMaxId;
    private int unreadCount;
    private TLAbsPeerNotifySettings notifySettings;
    private int pts;
    private TLAbsDraftMessage draft;

    /**
     * Instantiates a new TL dialog.
     */
    public TLDialog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public int getTopMessage() {
        return topMessage;
    }

    public int getReadInboxMaxId() {
        return readInboxMaxId;
    }

    public int getReadOutboxMaxId() {
        return readOutboxMaxId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public int getPts() {
        return pts;
    }

    public TLAbsDraftMessage getDraft() {
        return draft;
    }

    public boolean isPinned() {
        return (flags & FLAG_PINNED) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(topMessage, stream);
        StreamingUtils.writeInt(readInboxMaxId, stream);
        StreamingUtils.writeInt(readOutboxMaxId, stream);
        StreamingUtils.writeInt(unreadCount, stream);
        StreamingUtils.writeTLObject(notifySettings, stream);
        if ((flags & FLAG_PTS) != 0) {
            StreamingUtils.writeInt(pts, stream);
        }
        if ((flags & FLAG_DRAFT) != 0) {
            StreamingUtils.writeTLObject(draft, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        peer = ((TLAbsPeer) StreamingUtils.readTLObject(stream, context));
        topMessage = StreamingUtils.readInt(stream);
        readInboxMaxId = StreamingUtils.readInt(stream);
        readOutboxMaxId = StreamingUtils.readInt(stream);
        unreadCount = StreamingUtils.readInt(stream);
        notifySettings = (TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context);
        if ((flags & FLAG_PTS) != 0) {
            pts = StreamingUtils.readInt(stream);
        }
        if ((flags & FLAG_DRAFT) != 0) {
            draft = (TLAbsDraftMessage) StreamingUtils.readTLObject(stream, context);
        }
    }

    public String toString() {
        return "dialog#66ffba14";
    }
}