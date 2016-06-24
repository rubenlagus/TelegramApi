/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.telegram.api.messages.stickers;

/**
 * The type TL all stickers not modified.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAllStickersNotModified
 * @date 9 /01/15
 */
public class TLAllStickersNotModified extends TLAbsAllStickers {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe86602c3;

    /**
     * Instantiates a new TL all stickers not modified.
     */
    public TLAllStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }


    @Override
    public String toString() {
        return "allStickersNotModified#e86602c3";
    }
}
