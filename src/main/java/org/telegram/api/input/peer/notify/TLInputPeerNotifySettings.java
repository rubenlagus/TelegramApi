package org.telegram.api.input.peer.notify;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input peer notify settings.
 */
public class TLInputPeerNotifySettings extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x38935eb2;

    private static final int FLAG_SHOW_PREVIEWS = 0x00000001; // 0
    private static final int FLAG_SILENT        = 0x00000002; // 1

    private int flags;
    private int muteUntil;
    private String sound;

    /**
     * Instantiates a new TL input peer notify settings.
     */
    public TLInputPeerNotifySettings() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets mute until.
     *
     * @return the mute until
     */
    public int getMuteUntil() {
        return this.muteUntil;
    }

    /**
     * Sets mute until.
     *
     * @param value the value
     */
    public void setMuteUntil(int value) {
        this.muteUntil = value;
    }

    /**
     * Gets sound.
     *
     * @return the sound
     */
    public String getSound() {
        return this.sound;
    }

    /**
     * Sets sound.
     *
     * @param value the value
     */
    public void setSound(String value) {
        this.sound = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.muteUntil, stream);
        StreamingUtils.writeTLString(this.sound, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.muteUntil = StreamingUtils.readInt(stream);
        this.sound = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputPeerNotifySettings#38935eb2";
    }
}