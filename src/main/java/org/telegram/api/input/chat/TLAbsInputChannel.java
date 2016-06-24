package org.telegram.api.input.chat;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Abstract representation of input chat
 * @date 19 of September of 2015
 */
public abstract class TLAbsInputChannel extends TLObject{
    public abstract int getChannelId();
}
