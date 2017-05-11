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
 */
public class TLDocumentAttributeVideo extends TLAbsDocumentAttribute {
    public static final int CLASS_ID = 0xef02ce6;

    private static final int FLAG_ROUND_MESSAGE   = 0x00000001; // 0

    private int flags;
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

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isRoundMessage() {
        return (flags & FLAG_ROUND_MESSAGE) != 0;
    }

    public void setRoundMessage(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_ROUND_MESSAGE;
        } else {
            this.flags &= ~FLAG_ROUND_MESSAGE;
        }
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.duration = StreamingUtils.readInt(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeVideo#ef02ce6";
    }
}
