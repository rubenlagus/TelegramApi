package org.telegram.api.messages.chats;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public abstract class TLAbsMessagesChats extends TLObject {
    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats;

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }
}
