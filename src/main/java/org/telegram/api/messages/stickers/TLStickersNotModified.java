/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.messages.stickers;

/**
 * The type TL stickers not modified.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLStickersNotModified
 * @date 9 /01/15
 */
public class TLStickersNotModified extends TLAbsStickers {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf1749a22;

    /**
     * Instantiates a new TL stickers not modified.
     */
    public TLStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }


    @Override
    public String toString() {
        return "StickersNotModified#f1749a22";
    }
}
