package org.telegram.api.channel.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel messages filter collapsed
 * @date 18 of September of 2015
 */
public class TLChannelMessagesFilterCollapsed extends TLAbsChannelMessagesFilter {
    public static final int CLASS_ID = 0xfa01232e;

    public TLChannelMessagesFilterCollapsed() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.messages.filter.TLChannelMessagesFilterCollapsed#fa01232e";
    }
}
