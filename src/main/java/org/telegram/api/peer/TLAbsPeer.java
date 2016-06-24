package org.telegram.api.peer;

import org.telegram.tl.TLObject;

/**
 * The type TL abs peer.
 */
public abstract class TLAbsPeer extends TLObject {

    protected int id;

    /**
     * Instantiates a new TL abs peer.
     */
    protected TLAbsPeer() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}