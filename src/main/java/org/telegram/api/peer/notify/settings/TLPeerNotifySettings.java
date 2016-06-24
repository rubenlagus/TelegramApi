package org.telegram.api.peer.notify.settings;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL peer notify settings.
 */
public class TLPeerNotifySettings extends TLAbsPeerNotifySettings {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9acda4c0;

    private static final int FLAG_SHOW_PREVIEWS = 0x00000001; // 0
    private static final int FLAG_SILENT        = 0x00000002; // 1

    private int flags;
    /**
     * The Mute until.
     */
    private int muteUntil;
    /**
     * The Sound.
     */
    private String sound;

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
     * @param muteUntil the mute until
     */
    public void setMuteUntil(int muteUntil) {
        this.muteUntil = muteUntil;
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
     * @param sound the sound
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * Instantiates a new TL peer notify settings.
     */
    public TLPeerNotifySettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.muteUntil, stream);
        StreamingUtils.writeTLString(this.sound, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.muteUntil = StreamingUtils.readInt(stream);
        this.sound = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "peerNotifySettings#9acda4c0";
    }
}