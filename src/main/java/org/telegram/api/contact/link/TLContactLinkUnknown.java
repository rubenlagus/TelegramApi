/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contact.link;

/**
 * Current user added no known connection with the other user
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactLinkUnknown extends TLAbsContactLink {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5f4f9247;

    /**
     * Instantiates a new TL contact link unknown.
     */
    public TLContactLinkUnknown() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "contacts.contactLinkHasPhone#5f4f9247";
    }
}