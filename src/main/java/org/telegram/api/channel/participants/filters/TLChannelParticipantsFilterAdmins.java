package org.telegram.api.channel.participants.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participants filters admins
 * @date 24 of September of 2015
 */
public class TLChannelParticipantsFilterAdmins extends TLAbsChannelParticipantsFilter {
    public static final int CLASS_ID = 0xb4608969;

    public TLChannelParticipantsFilterAdmins() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantsAdmins#b4608969";
    }
}
