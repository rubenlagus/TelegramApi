/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.tl;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLNull value
 * @date 13/11/14
 */
public class TLNull extends TLObject {
    public static final int CLASS_ID = 0x56730bcc;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "null#56730bcc";
    }
}