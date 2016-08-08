package org.telegram.api.draft;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLDraftMessageEmpty extends TLAbsDraftMessage {
    public static final int CLASS_ID = 0xba4baec5;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "draftMessageEmpty#ba4baec5";
    }
}
