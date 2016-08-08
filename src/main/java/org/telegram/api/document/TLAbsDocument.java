/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.document;

import org.telegram.tl.TLObject;

/**
 * Abstract class to represent a document.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public abstract class TLAbsDocument extends TLObject {

    protected long id; ///< Document id

    /**
     * Instantiates a new TL abs document.
     */
    public TLAbsDocument() {
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
}