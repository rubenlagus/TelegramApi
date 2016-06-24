/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.invite;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invitation to a chat that has already been accepted
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatInviteAlready extends TLAbsChatInvite {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5a686d7c;

    private TLAbsChat chat; ///< Information of the chat

    /**
     * Instantiates a new TL chat invite already.
     */
    public TLChatInviteAlready() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat.
     *
     * @return the chat
     */
    public TLAbsChat getChat() {
        return this.chat;
    }

    /**
     * Sets chat.
     *
     * @param chat the chat
     */
    public void setChat(TLAbsChat chat) {
        this.chat = chat;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeTLObject(this.chat, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.chat = (TLAbsChat) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "chat.chatInviteAlready#5a686d7c";
    }
}
