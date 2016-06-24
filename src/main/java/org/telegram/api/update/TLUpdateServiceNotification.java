package org.telegram.api.update;

import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLIntVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update service notification.
 */
public class TLUpdateServiceNotification extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x382dd3e4;

    private String type;
    private boolean popup;
    private TLAbsMessageMedia media;
    private TLIntVector messages;

    /**
     * Instantiates a new TL update service notification.
     */
    public TLUpdateServiceNotification() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Is popup.
     *
     * @return the boolean
     */
    public boolean isPopup() {
        return this.popup;
    }

    /**
     * Sets popup.
     *
     * @param popup the popup
     */
    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public TLAbsMessageMedia getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(TLAbsMessageMedia media) {
        this.media = media;
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public TLIntVector getMessages() {
        return this.messages;
    }

    /**
     * Sets messages.
     *
     * @param messages the messages
     */
    public void setMessages(TLIntVector messages) {
        this.messages = messages;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLObject(this.media, stream);
        StreamingUtils.writeTLBool(this.popup, stream);

    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.type = StreamingUtils.readTLString(stream);
        this.messages = StreamingUtils.readTLIntVector(stream, context);
        this.media = (TLAbsMessageMedia) StreamingUtils.readTLObject(stream, context);
        this.popup = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "updateServiceNotification#382dd3e4";
    }
}