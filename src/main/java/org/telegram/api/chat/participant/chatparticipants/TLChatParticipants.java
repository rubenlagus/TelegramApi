/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat.participant.chatparticipants;

import org.telegram.api.chat.participant.chatparticipant.TLAbsChatParticipant;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Chat participants information of a chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatParticipants extends TLAbsChatParticipants {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3f460fed;

    private TLVector<TLAbsChatParticipant> participants; ///< List of participants
    private int version; ///< Chat version id

    /**
     * Instantiates a new TL chat participants.
     */
    public TLChatParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public TLVector<TLAbsChatParticipant> getParticipants() {
        return this.participants;
    }

    /**
     * Sets participants.
     *
     * @param participants the participants
     */
    public void setParticipants(TLVector<TLAbsChatParticipant> participants) {
        this.participants = participants;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLVector(this.participants, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.participants = (TLVector<TLAbsChatParticipant>) StreamingUtils.readTLVector(stream, context);
        this.version = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatParticipants#3f460fed";
    }
}