package org.telegram.api.toppeer.category;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeerCategoryGroups extends TLAbsTopPeerCategory {
    public static final int CLASS_ID = 0xbd17a14a;

    public TLTopPeerCategoryGroups() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryGroups#bd17a14a";
    }
}
