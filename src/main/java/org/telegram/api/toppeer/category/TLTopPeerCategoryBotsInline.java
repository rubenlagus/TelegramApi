package org.telegram.api.toppeer.category;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeerCategoryBotsInline extends TLAbsTopPeerCategory {
    public static final int CLASS_ID = 0x148677e2;

    public TLTopPeerCategoryBotsInline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryBotsInline#148677e2";
    }
}
