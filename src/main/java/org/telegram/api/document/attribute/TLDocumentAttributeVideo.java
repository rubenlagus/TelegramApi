/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicate the duration and size of a video
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLDocumentAttributeVideo
 * @date 9 /01/15
 */
public class TLDocumentAttributeVideo extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5910cccb;

    private int duration; ///< Duration of the videw in seconds
    private int w; ///< Width in pixels
    private int h; ///< Height in pixels

    /**
     * Instantiates a new TL document attribute video.
     */
    public TLDocumentAttributeVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return this.w;
    }

    /**
     * Sets w.
     *
     * @param w the w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return this.h;
    }

    /**
     * Sets h.
     *
     * @param h the h
     */
    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.duration = StreamingUtils.readInt(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeVideo#5910cccb";
    }
}
