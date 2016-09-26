package org.telegram.api.functions.messages;

import org.telegram.api.input.sticker.media.TLAbsInputStickeredMedia;
import org.telegram.api.sticker.stickersetconvered.TLAbsStickerSetCovered;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetAttachedStickers extends TLMethod<TLVector<TLAbsStickerSetCovered>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcc5b67cc;

    private TLAbsInputStickeredMedia media;

    /**
     * Instantiates a new TL request messages get chats.
     */
    public TLRequestMessagesGetAttachedStickers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsStickerSetCovered> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    public TLAbsInputStickeredMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputStickeredMedia media) {
        this.media = media;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(media, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        media = StreamingUtils.readTLObject(stream, context, TLAbsInputStickeredMedia.class);
    }

    public String toString() {
        return "messages.getAttachedStickers#cc5b67cc";
    }
}