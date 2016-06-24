/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.sendmessage.action;

/**
 * The type TL send message record audio action.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLSendMessageRecordAudioAction
 * @date 12 /11/14
 */
public class TLSendMessageRecordAudioAction extends TLAbsSendMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd52f73f7;

    /**
     * Instantiates a new TL send message record audio action.
     */
    public TLSendMessageRecordAudioAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "sendMessageRecordAudioAction#d52f73f7";
    }
}
