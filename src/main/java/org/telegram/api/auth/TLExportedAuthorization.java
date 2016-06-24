/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.auth;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Data for copy authorization between data centers
 * @author Ruben Bermudez
 * @version 2.0
 * @date 12 /11/14
 */
public class TLExportedAuthorization extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdf969c2d;

    private int id; ///< Current user identifier
    private TLBytes bytes; ///< Authorized key

    /**
     * Instantiates a new TL exported authorization.
     */
    public TLExportedAuthorization() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets bytes.
     *
     * @return the bytes
     */
    public TLBytes getBytes() {
        return this.bytes;
    }

    /**
     * Sets bytes.
     *
     * @param value the value
     */
    public void setBytes(TLBytes value) {
        this.bytes = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "auth.exportedAuthorization#df969c2d";
    }
}