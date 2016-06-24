package org.telegram.api.input.chat;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Input chat empty
 * @date 19 of September of 2015
 */
public class TLInputChannelEmpty extends TLAbsInputChannel {
    public static final int CLASS_ID = 0xee8c1e86;

    public TLInputChannelEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "input.chat.TLInputChannelEmpty#ee8c1e86";
    }

    @Override
    public int getChannelId() {
        return 0;
    }
}
