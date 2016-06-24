package org.telegram.api.channel.participants.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participants filters kicked
 * @date 24 of September of 2015
 */
public class TLChannelParticipantsFilterKicked extends TLAbsChannelParticipantsFilter {
    public static final int CLASS_ID = 0x3c37bb7a;

    public TLChannelParticipantsFilterKicked() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantsKicked#3c37bb7a";
    }
}
