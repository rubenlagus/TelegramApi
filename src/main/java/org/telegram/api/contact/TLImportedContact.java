/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contact;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent a succesfully imported contact
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLImportedContact extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd0028438;

    private int userId; ///< User id
    private long clientId; ///< Contact client identifier

    /**
     * Instantiates a new TL imported contact.
     */
    public TLImportedContact() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public long getClientId() {
        return this.clientId;
    }

    /**
     * Sets client id.
     *
     * @param value the value
     */
    public void setClientId(long value) {
        this.clientId = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeLong(this.clientId, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.clientId = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "importedContact#d0028438";
    }
}