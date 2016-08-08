package org.telegram.api.messages.stickers.featured;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLMessagesFeaturedStickersNotModified extends TLAbsMessagesFeaturedStickers {
    public static final int CLASS_ID = 0x4ede3cf;

    public TLMessagesFeaturedStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.featuredStickersNotModified#4ede3cf";
    }
}
