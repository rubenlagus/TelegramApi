/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.document.attribute;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicate the duration of an audio document
 * @author Ruben Bermudez
 * @version 2.0
 * @date 9 /01/15
 */
public class TLDocumentAttributeAudio extends TLAbsDocumentAttribute {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9852f9c6;

    private static final int FLAG_TITLE         = 0x00000001; // 0
    private static final int FLAG_PERFORMER     = 0x00000002; // 1
    private static final int FLAG_WAVEFORM      = 0x00000004; // 2
    private static final int FLAG_UNUSED3       = 0x00000008; // 3
    private static final int FLAG_UNUSED4       = 0x00000010; // 4
    private static final int FLAG_UNUSED5       = 0x00000020; // 5
    private static final int FLAG_UNUSED6       = 0x00000040; // 6
    private static final int FLAG_UNUSED7       = 0x00000080; // 7
    private static final int FLAG_UNUSED8       = 0x00000100; // 8
    private static final int FLAG_UNUSED9       = 0x00000200; // 9
    private static final int FLAG_VOICE         = 0x00000400; // 10

    private int flags;
    private int duration; ///< Duration of the audio in seconds
    private String title;
    private String performer;
    private TLBytes waveform;

    /**
     * Instantiates a new TL document attribute audio.
     */
    public TLDocumentAttributeAudio() {
        super();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public TLBytes getWaveform() {
        return waveform;
    }

    public void setWaveform(TLBytes waveform) {
        this.waveform = waveform;
    }

    public boolean isVoice() {
        return (this.flags & FLAG_VOICE) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.duration, stream);
        if ((this.flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if ((this.flags & FLAG_PERFORMER) != 0) {
            StreamingUtils.writeTLString(this.performer, stream);
        }
        if ((this.flags & FLAG_WAVEFORM) != 0) {
            StreamingUtils.writeTLBytes(this.waveform, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.duration = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_TITLE) != 0) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_PERFORMER) != 0) {
            this.performer = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_WAVEFORM) != 0) {
            this.waveform = StreamingUtils.readTLBytes(stream, context);
        }
    }

    @Override
    public String toString() {
        return "documentAttributeAudio#9852f9c6";
    }
}
