/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.photo;

import org.telegram.api.file.location.TLAbsFileLocation;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent a chat photo
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatPhoto extends TLAbsChatPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6153276a;

    private TLAbsFileLocation photo_small; ///< Location of the file corresponding to the small thumbnail for group profile photo
    private TLAbsFileLocation photo_big; ///< Location of the file corresponding to the big thumbnail for group profile photo

    /**
     * Instantiates a new TL chat photo.
     */
    public TLChatPhoto() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets photo _ small.
     *
     * @return the photo _ small
     */
    public TLAbsFileLocation getPhoto_small() {
        return this.photo_small;
    }

    /**
     * Sets photo _ small.
     *
     * @param photo_small the photo _ small
     */
    public void setPhoto_small(TLAbsFileLocation photo_small) {
        this.photo_small = photo_small;
    }

    /**
     * Gets photo _ big.
     *
     * @return the photo _ big
     */
    public TLAbsFileLocation getPhoto_big() {
        return this.photo_big;
    }

    /**
     * Sets photo _ big.
     *
     * @param photo_big the photo _ big
     */
    public void setPhoto_big(TLAbsFileLocation photo_big) {
        this.photo_big = photo_big;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.photo_small, stream);
        StreamingUtils.writeTLObject(this.photo_big, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.photo_small = ((TLAbsFileLocation) StreamingUtils.readTLObject(stream, context));
        this.photo_big = ((TLAbsFileLocation) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "chatPhoto#6153276a";
    }
}