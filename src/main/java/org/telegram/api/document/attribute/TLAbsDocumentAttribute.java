/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.tl.TLObject;

/**
 * Abstract class to represent an attribute of a document
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public abstract class TLAbsDocumentAttribute extends TLObject {
    /**
     * Instantiates a new TL abs document attribute.
     */
    protected TLAbsDocumentAttribute() {
        super();
    }
}
