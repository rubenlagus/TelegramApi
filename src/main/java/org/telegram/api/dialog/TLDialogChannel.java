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
public class TLDialogChannel extends TLAbsDialog {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5b8496b2;

    private int topImportantMessage;
    private int unreadImportantCount;
    private int pts;

    /**
     * Instantiates a new TL dialog.
     */
    public TLDialogChannel() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsPeer value) {
        this.peer = value;
    }

    /**
     * Gets top message.
     *
     * @return the top message
     */
    public int getTopMessage() {
        return this.topMessage;
    }

    /**
     * Sets top message.
     *
     * @param value the value
     */
    public void setTopMessage(int value) {
        this.topMessage = value;
    }

    /**
     * Gets unread count.
     *
     * @return the unread count
     */
    public int getUnreadCount() {
        return this.unreadCount;
    }

    /**
     * Sets unread count.
     *
     * @param value the value
     */
    public void setUnreadCount(int value) {
        this.unreadCount = value;
    }

    /**
     * Gets notify settings.
     *
     * @return the notify settings
     */
    public TLAbsPeerNotifySettings getNotifySettings() {
        return this.notifySettings;
    }

    /**
     * Sets notify settings.
     *
     * @param notifySettings the notify settings
     */
    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    /**
     * Gets read inbox max id.
     *
     * @return the read inbox max id
     */
    public int getReadInboxMaxId() {
        return this.readInboxMaxId;
    }

    /**
     * Sets read inbox max id.
     *
     * @param readInboxMaxId the read inbox max id
     */
    public void setReadInboxMaxId(int readInboxMaxId) {
        this.readInboxMaxId = readInboxMaxId;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.topMessage, stream);
        StreamingUtils.writeInt(this.topImportantMessage, stream);
        StreamingUtils.writeInt(this.readInboxMaxId, stream);
        StreamingUtils.writeInt(this.unreadCount, stream);
        StreamingUtils.writeInt(this.unreadImportantCount, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        StreamingUtils.writeInt(this.pts, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsPeer) StreamingUtils.readTLObject(stream, context));
        this.topMessage = StreamingUtils.readInt(stream);
        this.topImportantMessage = StreamingUtils.readInt(stream);
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.unreadImportantCount = StreamingUtils.readInt(stream);
        this.notifySettings = (TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context);
        this.pts = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "dialog.TLDialogChannel#5b8496b2";
    }
}