package org.telegram.api.input.document;

import org.telegram.tl.TLObject;

/**
 * The type TL abs input document.
 */
public abstract class TLAbsInputDocument extends TLObject {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Access hash.
     */
    protected long accessHash;

    /**
     * Instantiates a new TL abs input document.
     */
    protected TLAbsInputDocument() {
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
}