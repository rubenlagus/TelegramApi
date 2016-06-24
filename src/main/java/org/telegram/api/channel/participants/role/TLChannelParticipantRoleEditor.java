package org.telegram.api.channel.participants.role;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participant role editor
 * @date 18 of September of 2015
 */
public class TLChannelParticipantRoleEditor extends TLAbsChannelParticipantRole {
    public static final int CLASS_ID = 0x820bfe8c;

    public TLChannelParticipantRoleEditor() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantRoleEditor#820bfe8c";
    }
}
