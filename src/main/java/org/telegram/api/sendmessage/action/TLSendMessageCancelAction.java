/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.sendmessage.action;

/**
 * The type TL send message cancel action.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLSendMessageCancelAction
 * @date 12 /11/14
 */
public class TLSendMessageCancelAction extends TLAbsSendMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfd5ec8f5;

    /**
     * Instantiates a new TL send message cancel action.
     */
    public TLSendMessageCancelAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "sendMessageCancelAction#fd5ec8f5";
    }
}
