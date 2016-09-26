/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

/**
 * Indates that a document has stickers
 * @author Ruben Bermudez
 * @version 2.0
 */
public class TLDocumentAttributeHasStickers extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9801d2f7;

    /**
     * Instantiates a new TL document attribute animated.
     */
    public TLDocumentAttributeHasStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "documentAttributeHasStickers#9801d2f7";
    }
}
