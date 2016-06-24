/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.notify.peer;

/**
 * The type TL notify users.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLNotifyUsers
 * @date 13 /11/14
 */
public class TLNotifyUsers extends TLAbsNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb4c83b4c;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "notifyUsers#b4c83b4c";
    }
}
