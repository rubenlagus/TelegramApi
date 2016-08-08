package org.telegram.api.toppeer.category;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeerCategoryChannels extends TLAbsTopPeerCategory {
    public static final int CLASS_ID = 0x161d9628;

    public TLTopPeerCategoryChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryChannels#161d9628";
    }
}
