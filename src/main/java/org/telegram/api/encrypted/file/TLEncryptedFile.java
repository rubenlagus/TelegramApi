/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.encrypted.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * File sent to a encrypted chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLEncryptedFile extends TLAbsEncryptedFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4a70994c;

    private long accessHash; ///< Checking sum depending on user ID
    private int size; ///< File size in bytes
    private int dcId; ///< Datacenter number where it is stored
    private int keyFingerprint; ///< 32-bit fingerprint of key used for file encryption

    /**
     * Instantiates a new TL encrypted file.
     */
    public TLEncryptedFile() {
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
     * Gets dc id.
     *
     * @return the dc id
     */
    public int getDcId() {
        return this.dcId;
    }

    /**
     * Sets dc id.
     *
     * @param dcId the dc id
     */
    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    /**
     * Gets key fingerprint.
     *
     * @return the key fingerprint
     */
    public int getKeyFingerprint() {
        return this.keyFingerprint;
    }

    /**
     * Sets key fingerprint.
     *
     * @param keyFingerprint the key fingerprint
     */
    public void setKeyFingerprint(int keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    /**
     * Gets access _ hash.
     *
     * @return the access _ hash
     */
    public long getAccess_hash() {
        return this.accessHash;
    }

    /**
     * Sets access _ hash.
     *
     * @param accessHash the access hash
     */
    public void setAccess_hash(long accessHash) {
        this.accessHash = accessHash;
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
     * Gets dc _ id.
     *
     * @return the dc _ id
     */
    public int getDc_id() {
        return this.dcId;
    }

    /**
     * Sets dc _ id.
     *
     * @param dcId the dc id
     */
    public void setDc_id(int dcId) {
        this.dcId = dcId;
    }

    /**
     * Gets key _ fingerprint.
     *
     * @return the key _ fingerprint
     */
    public int getKey_fingerprint() {
        return this.keyFingerprint;
    }

    /**
     * Sets key _ fingerprint.
     *
     * @param keyFingerprint the key fingerprint
     */
    public void setKey_fingerprint(int keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeInt(this.keyFingerprint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.size = StreamingUtils.readInt(stream);
        this.dcId = StreamingUtils.readInt(stream);
        this.keyFingerprint = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "encryptedFile#4a70994c";
    }
}