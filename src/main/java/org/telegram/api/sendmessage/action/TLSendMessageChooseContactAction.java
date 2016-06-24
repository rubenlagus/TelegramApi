/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.sendmessage.action;

/**
 * The type TL send message choose contact action.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLSendMessageChooseContactAction
 * @date 12 /11/14
 */
public class TLSendMessageChooseContactAction extends TLAbsSendMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x628cbc6f;

    /**
     * Instantiates a new TL send message choose contact action.
     */
    public TLSendMessageChooseContactAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "sendMessageChooseContactAction#" + CLASS_ID;
    }
}
