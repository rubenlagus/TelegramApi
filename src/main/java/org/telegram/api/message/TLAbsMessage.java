package org.telegram.api.message;

import org.telegram.tl.TLObject;

/**
 * The type TL abs message.
 */
public abstract class TLAbsMessage extends TLObject {

    /**
     * Instantiates a new TL abs message.
     */
    protected TLAbsMessage() {
        super();
    }

    public abstract int getChatId();
}