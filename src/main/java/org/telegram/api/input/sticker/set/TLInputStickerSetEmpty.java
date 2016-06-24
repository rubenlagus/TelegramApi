package org.telegram.api.input.sticker.set;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of July of 2015
 */
public class TLInputStickerSetEmpty extends TLAbsInputStickerSet {
    public static final int CLASS_ID = 0xffb62b95;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "sticker.set.inputStickerSetEmpty#ffb62b95";
    }
}
