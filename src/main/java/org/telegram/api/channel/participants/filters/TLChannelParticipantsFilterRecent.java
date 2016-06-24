package org.telegram.api.channel.participants.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participants filters recent
 * @date 24 of September of 2015
 */
public class TLChannelParticipantsFilterRecent extends TLAbsChannelParticipantsFilter {
    public static final int CLASS_ID = 0xde3f3c79;

    public TLChannelParticipantsFilterRecent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantsRecent#de3f3c79";
    }
}
