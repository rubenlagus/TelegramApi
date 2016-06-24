/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.encrypted.chat;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Encrypted chat with another user
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLEncryptedChat extends TLAbsEncryptedChat {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa56ce36;

    private long accessHash; ///< Check sum dependant on the user ID
    private int date; ///< Chat creation date
    private int adminId; ///< Creator user id
    private int participantId; ///< Second participant user id
    /**
     * <code>B = g ^ b mod p</code>, if the currently authorized user is the chat's creator,
     * or <code>A = g ^ a mod p</code> otherwise
     *
     * @see http://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange
     */
    private TLBytes gAOrB;
    private long keyFingerprint; ///< 64-bit fingerprint of received key
    /**
     * Instantiates a new TL encrypted chat.
     */
    public TLEncryptedChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets admin id.
     *
     * @return the admin id
     */
    public int getAdminId() {
        return this.adminId;
    }

    /**
     * Sets admin id.
     *
     * @param adminId the admin id
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Gets participant id.
     *
     * @return the participant id
     */
    public int getParticipantId() {
        return this.participantId;
    }

    /**
     * Sets participant id.
     *
     * @param participantId the participant id
     */
    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    /**
     * Gets a or b.
     *
     * @return the a or b
     */
    public TLBytes getgAOrB() {
        return this.gAOrB;
    }

    /**
     * Sets a or b.
     *
     * @param gAOrB the g a or b
     */
    public void setgAOrB(TLBytes gAOrB) {
        this.gAOrB = gAOrB;
    }

    /**
     * Gets key fingerprint.
     *
     * @return the key fingerprint
     */
    public long getKeyFingerprint() {
        return this.keyFingerprint;
    }

    /**
     * Sets key fingerprint.
     *
     * @param keyFingerprint the key fingerprint
     */
    public void setKeyFingerprint(long keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.adminId, stream);
        StreamingUtils.writeInt(this.participantId, stream);
        StreamingUtils.writeTLBytes(this.gAOrB, stream);
        StreamingUtils.writeLong(this.keyFingerprint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.adminId = StreamingUtils.readInt(stream);
        this.participantId = StreamingUtils.readInt(stream);
        this.gAOrB = StreamingUtils.readTLBytes(stream, context);
        this.keyFingerprint = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "encryptedChat#fa56ce36";
    }
}