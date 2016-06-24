/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contact.link;

/**
 * Current user added has other user's phone
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactLinkHasPhone extends TLAbsContactLink {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x268f3f59;

    /**
     * Instantiates a new TL contact link has phone.
     */
    public TLContactLinkHasPhone() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "contacts.contactLinkHasPhone#268f3f59";
    }
}