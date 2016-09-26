package org.telegram.api.sticker.stickersetconvered;

import org.telegram.api.document.TLAbsDocument;
import org.telegram.api.sticker.set.TLStickerSet;
import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public abstract class TLAbsStickerSetCovered extends TLObject {
    protected TLStickerSet set;
    protected TLAbsDocument cover;

    public TLStickerSet getSet() {
        return set;
    }

    public TLAbsDocument getCover() {
        return cover;
    }
}
