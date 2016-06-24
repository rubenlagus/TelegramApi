/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contact.link;

/**
 * Empty connection between users
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactLinkNone extends TLAbsContactLink {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfeedd3ad;

    /**
     * Instantiates a new TL contact link none.
     */
    public TLContactLinkNone() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "contacts.contactLinkNone#feedd3ad";
    }
}