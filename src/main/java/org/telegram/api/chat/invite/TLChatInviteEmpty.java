/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.invite;

/**
 * Empty invitation to a chat.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatInviteEmpty extends TLAbsChatInvite {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x69df3769;

    /**
     * Instantiates a new TL chat invite empty.
     */
    public TLChatInviteEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "chat.chatInviteEmpty#69df3769";
    }
}
