package org.telegram.api.functions.messages;

import org.telegram.api.input.sticker.set.TLAbsInputStickerSet;
import org.telegram.api.messages.stickers.setintallresult.TLAbsMessagesStickerSetInstallResult;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get stickers.
 */
public class TLRequestMessagesInstallStickersSet extends TLMethod<TLAbsMessagesStickerSetInstallResult> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc78fe460;

    private TLAbsInputStickerSet stickerSet;
    private boolean archived;

    /**
     * Instantiates a new TL request messages get stickers.
     */
    public TLRequestMessagesInstallStickersSet() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getStickerSet() {
        return stickerSet;
    }

    public void setStickerSet(TLAbsInputStickerSet stickerSet) {
        this.stickerSet = stickerSet;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public TLAbsMessagesStickerSetInstallResult deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsMessagesStickerSetInstallResult))
            return (TLAbsMessagesStickerSetInstallResult) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsMessagesStickerSetInstallResult.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.stickerSet, stream);
        StreamingUtils.writeTLBool(this.archived, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.stickerSet = (TLAbsInputStickerSet) StreamingUtils.readTLObject(stream, context);
        this.archived = StreamingUtils.readTLBool(stream);
    }

    public String toString() {
        return "messages.installStickerSet#c78fe460";
    }
}