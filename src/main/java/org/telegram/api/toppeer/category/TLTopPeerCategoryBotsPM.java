package org.telegram.api.toppeer.category;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeerCategoryBotsPM extends TLAbsTopPeerCategory {
    public static final int CLASS_ID = 0xab661b5b;

    public TLTopPeerCategoryBotsPM() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryBotsPM#ab661b5b";
    }
}
