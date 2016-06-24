package org.telegram.api.user;

import org.telegram.tl.TLObject;

/**
 * The type TL abs user.
 */
public abstract class TLAbsUser extends TLObject {
    /**
     * The Id.
     */
    protected int id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}