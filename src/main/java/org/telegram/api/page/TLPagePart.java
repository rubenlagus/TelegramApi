package org.telegram.api.page;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.page.block.TLAbsPageBlock;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPagePart extends TLAbsPage {
    public static final int CLASS_ID = 0x8dee6c44;

    private TLVector<TLAbsPageBlock> blocks;
    private TLVector<TLAbsPhoto> photos;
    private TLVector<TLAbsDocument> videos;

    public TLPagePart() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public TLVector<TLAbsPhoto> getPhotos() {
        return photos;
    }

    public TLVector<TLAbsDocument> getVideos() {
        return videos;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(blocks, stream);
        StreamingUtils.writeTLVector(photos, stream);
        StreamingUtils.writeTLVector(videos, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        photos = StreamingUtils.readTLVector(stream, context, TLAbsPhoto.class);
        videos = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "pagePart#8dee6c44";
    }
}
