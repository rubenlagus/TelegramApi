package org.telegram.api.messages.stickers.recent;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLMessagesRecentStickersNotModified extends TLObject {
    public static final int CLASS_ID = 0xb17f890;

    public TLMessagesRecentStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.recentStickersNotModified#b17f890";
    }
}
