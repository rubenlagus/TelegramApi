package org.telegram.bot.structure;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public interface Chat {
    /**
     * Returns chat id
     * @return Id of the chat
     */
    int getId();

    /**
     * Returns chat access hash
     * @return Access hash of this chat
     */
    Long getAccessHash();

    /**
     * Returns true if the chat is a channel, false otherwise
     * @return true if the chat is a channel, false otherwise
     */
    boolean isChannel();
}

