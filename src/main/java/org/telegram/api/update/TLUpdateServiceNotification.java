package org.telegram.api.update;

import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update service notification.
 */
public class TLUpdateServiceNotification extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xebe46819;

    private static final int FLAG_POPUP          = 0x00000001; // 0
    private static final int FLAG_INBOX_DATE     = 0x00000002; // 1

    private int flags;
    private int inboxDate;
    private String type;
    private String message;
    private TLAbsMessageMedia media;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL update service notification.
     */
    public TLUpdateServiceNotification() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public TLAbsMessageMedia getMedia() {
        return media;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public boolean isPopup() {
        return (flags & FLAG_POPUP) != 0;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_INBOX_DATE) != 0) {
            StreamingUtils.writeInt(inboxDate, stream);
        }
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLObject(this.media, stream);
        StreamingUtils.writeTLVector(this.entities, stream);

    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_INBOX_DATE) != 0) {
            inboxDate = StreamingUtils.readInt(stream);
        }
        this.type = StreamingUtils.readTLString(stream);
        this.message = StreamingUtils.readTLString(stream);
        this.media = StreamingUtils.readTLObject(stream, context, TLAbsMessageMedia.class);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
    }

    public String toString() {
        return "updateServiceNotification#ebe46819";
    }
}