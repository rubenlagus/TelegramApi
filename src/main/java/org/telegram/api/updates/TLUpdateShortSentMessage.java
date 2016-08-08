package org.telegram.api.updates;

import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update short sent message.
 */
public class TLUpdateShortSentMessage extends TLAbsUpdates {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11f1331c;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_OUT                = 0x00000002; // 1
    private static final int FLAG_UNUSED2            = 0x00000004; // 2
    private static final int FLAG_UNUSED3            = 0x00000008; // 3
    private static final int FLAG_UNUSED4            = 0x00000010; // 4
    private static final int FLAG_UNUSED5            = 0x00000020; // 5
    private static final int FLAG_UNUSED6            = 0x00000040; // 6
    private static final int FLAG_ENTITIES           = 0x00000080; // 7
    private static final int FLAG_UNUSED8            = 0x00000100; // 8
    private static final int FLAG_MEDIA              = 0x00000200; // 9

    private int flags;
    private int id;
    private int pts;
    private int ptsCount;
    private int date;
    private TLAbsMessageMedia media;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL update short sent message.
     */
    public TLUpdateShortSentMessage() {
        super();
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
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    public TLAbsMessageMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsMessageMedia media) {
        this.media = media;
    }

    public boolean hasMedia() {
        return (this.flags & FLAG_MEDIA) != 0;
    }

    /**
     * Is sent.
     *
     * @return the boolean
     */
    public boolean isSent() {
        return (this.flags & FLAG_OUT) != 0;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            StreamingUtils.writeTLObject(this.media, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            this.media = (TLAbsMessageMedia) StreamingUtils.readTLObject(stream, context);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = (TLVector<TLAbsMessageEntity>) StreamingUtils.readTLVector(stream, context);
        }
    }

    public String toString() {
        return "updateShortSentMessage#11f1331c";
    }
}