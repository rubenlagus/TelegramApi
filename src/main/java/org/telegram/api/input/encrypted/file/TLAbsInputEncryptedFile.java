package org.telegram.api.input.encrypted.file;

import org.telegram.tl.TLBytes;
import org.telegram.tl.TLObject;

/**
 * The type TL abs input encrypted file.
 */
public abstract class TLAbsInputEncryptedFile extends TLObject {
    /**
     * The Id.
     */
    public long id;
    /**
     * The Access hash.
     */
    public long accessHash;
    /**
     * The Parts.
     */
    public int parts;
    /**
     * The Key fingerprint.
     */
    public int keyFingerprint;
    /**
     * The Md 5 checksum.
     */
    public String md5Checksum;
    /**
     * The Key.
     */
    public TLBytes key;
    /**
     * The Iv.
     */
    public TLBytes iv;

    /**
     * Instantiates a new TL abs input encrypted file.
     */
    protected TLAbsInputEncryptedFile() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets parts.
     *
     * @return the parts
     */
    public int getParts() {
        return this.parts;
    }

    /**
     * Sets parts.
     *
     * @param parts the parts
     */
    public void setParts(int parts) {
        this.parts = parts;
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
     * Gets md 5 checksum.
     *
     * @return the md 5 checksum
     */
    public String getMd5Checksum() {
        return this.md5Checksum;
    }

    /**
     * Sets md 5 checksum.
     *
     * @param md5Checksum the md 5 checksum
     */
    public void setMd5Checksum(String md5Checksum) {
        this.md5Checksum = md5Checksum;
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
}