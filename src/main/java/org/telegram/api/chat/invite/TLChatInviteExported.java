/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.invite;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invitation to a chat via link.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatInviteExported extends TLAbsChatInvite {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfc2e05bc;

    private String link; ///< Invitation link

    /**
     * Instantiates a new TL chat invite exported.
     */
    public TLChatInviteExported() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeTLString(this.link, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.link = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "chat.chatInviteExported#fc2e05bc";
    }
}
