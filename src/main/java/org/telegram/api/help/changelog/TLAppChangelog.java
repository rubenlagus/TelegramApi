package org.telegram.api.help.changelog;

import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.message.media.TLAbsMessageMedia;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL app changelog.
 */
public class TLAppChangelog extends TLAbsAppChangelog {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2a137e7c;

    private String message;
    private TLAbsMessageMedia media;
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Instantiates a new TL app changelog.
     */
    public TLAppChangelog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLObject(this.media, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.message = StreamingUtils.readTLString(stream);
        this.media = StreamingUtils.readTLObject(stream, context, TLAbsMessageMedia.class);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
    }

    public String toString() {
        return "help.appChangelog#2a137e7c";
    }
}