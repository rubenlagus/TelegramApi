/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Audio media content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessageMediaAudio extends TLAbsDecryptedMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x57e0a9cb;

    private int duration; ///< Audio duration in seconds
    private String mimeType; ///< MIME-type of the audio file
    private int size; ///< File size
    private TLBytes key; ///< Key to decrypt the attached media file
    private TLBytes iv; ///< Initialization vector

    /**
     * Instantiates a new TL decrypted message media audio.
     */
    public TLDecryptedMessageMediaAudio() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param mimeType the mime type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public TLBytes getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLBytes key) {
        this.key = key;
    }

    /**
     * Gets iv.
     *
     * @return the iv
     */
    public TLBytes getIv() {
        return this.iv;
    }

    /**
     * Sets iv.
     *
     * @param iv the iv
     */
    public void setIv(TLBytes iv) {
        this.iv = iv;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLBytes(this.key, stream);
        StreamingUtils.writeTLBytes(this.iv, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.duration = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.size = StreamingUtils.readInt(stream);
        this.key = StreamingUtils.readTLBytes(stream, context);
        this.iv = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "decryptedMessageMediaAudio#57e0a9cb";
    }
}