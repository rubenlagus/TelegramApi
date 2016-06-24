package org.telegram.api.channel.participants.role;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participant role empty
 * @date 18 of September of 2015
 */
public class TLChannelParticipantRoleEmpty extends TLAbsChannelParticipantRole {
    public static final int CLASS_ID = 0xb285a0c6;

    public TLChannelParticipantRoleEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantRoleEmpty#b285a0c6";
    }
}
