package org.telegram.api.contacts.toppeers;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLContactsTopPeersNotModified extends TLAbsContactsTopPeers {
    public static final int CLASS_ID = 0xde266ef5;

    public TLContactsTopPeersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "contacts.topPeersNotModified#de266ef5";
    }
}
