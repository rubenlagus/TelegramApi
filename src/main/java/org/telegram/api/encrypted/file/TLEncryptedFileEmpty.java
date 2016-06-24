/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.encrypted.file;

/**
 * Empty file sent to a encrypted chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLEncryptedFileEmpty extends TLAbsEncryptedFile {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc21f497e;

    /**
     * Instantiates a new TL encrypted file empty.
     */
    public TLEncryptedFileEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "encryptedFileEmpty#c21f497e";
    }
}