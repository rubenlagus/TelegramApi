/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contents of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessage extends TLAbsDecryptedMessage {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x204d3878;

    /**
     * Message lifetime. Has higher priority than decryptedMessageActionSetMessageTTL.
     *
     * @see org.telegram.api.decryptedmessage.TLDecryptedMessageActionSetMessageTTL
     */
    private int ttl;
    private String message; ///< Message text
    private TLAbsDecryptedMessageMedia media; ///< Media content

    /**
     * Instantiates a new TL decrypted message.
     */
    public TLDecryptedMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets ttl.
     *
     * @return the ttl
     */
    public int getTtl() {
        return this.ttl;
    }

    /**
     * Sets ttl.
     *
     * @param ttl the ttl
     */
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public TLAbsDecryptedMessageMedia getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(TLAbsDecryptedMessageMedia media) {
        this.media = media;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeInt(this.ttl, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLObject(this.media, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.randomId = StreamingUtils.readLong(stream);
        this.ttl = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        this.media = ((TLAbsDecryptedMessageMedia) StreamingUtils.readTLObject(stream, context));
    }

    @Override
    public String toString() {
        return "decryptedMessage#204d3878";
    }
}