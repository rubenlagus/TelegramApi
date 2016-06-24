/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts;

import org.telegram.api.contact.link.TLAbsContactLink;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Connections between a certain user and the current one.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactsLink extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3ace484c;

    private TLAbsContactLink myLink; ///< Connection of the current user to the user
    private TLAbsContactLink foreignLink; ///< Connection of the user to the current user
    private TLAbsUser user; ///< Referenced user

    /**
     * Instantiates a new TL contacts link.
     */
    public TLContactsLink() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets my link.
     *
     * @return the my link
     */
    public TLAbsContactLink getMyLink() {
        return this.myLink;
    }

    /**
     * Sets my link.
     *
     * @param value the value
     */
    public void setMyLink(TLAbsContactLink value) {
        this.myLink = value;
    }

    /**
     * Gets foreign link.
     *
     * @return the foreign link
     */
    public TLAbsContactLink getForeignLink() {
        return this.foreignLink;
    }

    /**
     * Sets foreign link.
     *
     * @param value the value
     */
    public void setForeignLink(TLAbsContactLink value) {
        this.foreignLink = value;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public TLAbsUser getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param value the value
     */
    public void setUser(TLAbsUser value) {
        this.user = value;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.myLink, stream);
        StreamingUtils.writeTLObject(this.foreignLink, stream);
        StreamingUtils.writeTLObject(this.user, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.myLink = (TLAbsContactLink) StreamingUtils.readTLObject(stream, context);
        this.foreignLink = (TLAbsContactLink) StreamingUtils.readTLObject(stream, context);
        this.user = (TLAbsUser) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "contacts.link#3ace484c";
    }
}