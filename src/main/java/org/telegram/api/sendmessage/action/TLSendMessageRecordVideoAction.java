/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.sendmessage.action;

/**
 * The type TL send message record video action.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLSendMessageRecordVideoAction
 * @date 12 /11/14
 */
public class TLSendMessageRecordVideoAction extends TLAbsSendMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa187d66f;

    /**
     * Instantiates a new TL send message record video action.
     */
    public TLSendMessageRecordVideoAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "sendMessageRecordAudioAction#a187d66f";
    }
}
