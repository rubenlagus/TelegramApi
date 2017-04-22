package org.telegram.api.page.block;

import org.telegram.api.richtext.TLAbsRichText;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockEmbedPost extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x292c7be9;

    private String url;
    private long webpageId;
    private long authorPhotoId;
    private String author;
    private int date;
    private TLVector<TLAbsPageBlock> blocks;
    private TLAbsRichText caption;

    public TLPageBlockEmbedPost() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public long getWebpageId() {
        return webpageId;
    }

    public long getAuthorPhotoId() {
        return authorPhotoId;
    }

    public String getAuthor() {
        return author;
    }

    public int getDate() {
        return date;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public TLAbsRichText getCaption() {
        return caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeLong(webpageId, stream);
        StreamingUtils.writeLong(authorPhotoId, stream);
        StreamingUtils.writeTLString(author, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeTLVector(blocks, stream);
        StreamingUtils.writeTLObject(caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        url = StreamingUtils.readTLString(stream);
        webpageId = StreamingUtils.readLong(stream);
        authorPhotoId = StreamingUtils.readLong(stream);
        author = StreamingUtils.readTLString(stream);
        date = StreamingUtils.readInt(stream);
        blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        caption = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockEmbedPost#292c7be9";
    }
}
