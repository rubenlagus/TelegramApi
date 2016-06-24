package org.telegram.api.channel.participants.role;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participant role moderator
 * @date 18 of September of 2015
 */
public class TLChannelParticipantRoleModerator extends TLAbsChannelParticipantRole {
    public static final int CLASS_ID = 0x9618d975;

    public TLChannelParticipantRoleModerator() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantRoleModerator#9618d975";
    }
}
