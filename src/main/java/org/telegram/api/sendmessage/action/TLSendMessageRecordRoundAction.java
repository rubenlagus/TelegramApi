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
 */
public class TLSendMessageRecordRoundAction extends TLAbsSendMessageAction {
    public static final int CLASS_ID = 0x88f27fbc;

    public TLSendMessageRecordRoundAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "sendMessageRecordRoundAction#88f27fbc";
    }
}
