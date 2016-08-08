package org.telegram.api.toppeer.category;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeerCategoryCorrespondents extends TLAbsTopPeerCategory {
    public static final int CLASS_ID = 0x637b7ed;

    public TLTopPeerCategoryCorrespondents() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryCorrespondents#637b7ed";
    }
}
