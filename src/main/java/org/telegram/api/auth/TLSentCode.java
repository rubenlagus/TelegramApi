/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.auth;

import org.telegram.api.auth.codetype.TLAbsCodeType;
import org.telegram.api.auth.sentcodetype.TLAbsSentCodeType;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Confirmation of a logging code sent to the user.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 12 /11/14
 */
public class TLSentCode extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5e002502;

    private static final int FLAG_PHONE_REGISTERED = 0x00000001; // 0
    private static final int FLAG_NEXT_TYPE        = 0x00000002; // 1
    private static final int FLAG_TIMEOUT          = 0x00000004; // 2



    private int flags;
    private TLAbsSentCodeType type; ///< Type of this code
    private String phoneCodeHash; ///< Code sent hash
    private TLAbsCodeType nextType; ///< Next code type to send
    private int timeout; ///< Timeout until next code type is sent

    /**
     * Instantiates a new TL sent code.
     */
    public TLSentCode() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets phone code hash.
     *
     * @return the phone code hash
     */
    public String getPhoneCodeHash() {
        return this.phoneCodeHash;
    }

    public boolean isPhoneRegistered() {
        return (flags & FLAG_PHONE_REGISTERED) != 0;
    }

    /**
     * Sets phone code hash.
     *
     * @param phoneCodeHash the phone code hash
     */
    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    public TLAbsSentCodeType getType() {
        return type;
    }

    public void setType(TLAbsSentCodeType type) {
        this.type = type;
    }

    public TLAbsCodeType getNextType() {
        return nextType;
    }

    public void setNextType(TLAbsCodeType nextType) {
        this.nextType = nextType;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeTLObject(type, stream);
        StreamingUtils.writeTLString(phoneCodeHash, stream);
        if ((flags & FLAG_NEXT_TYPE) != 0) {
            StreamingUtils.writeTLObject(nextType, stream);
        }
        if ((flags & FLAG_TIMEOUT) != 0) {
            StreamingUtils.writeInt(this.timeout, stream);
        }
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        type = (TLAbsSentCodeType) StreamingUtils.readTLObject(stream, context);
        phoneCodeHash = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_NEXT_TYPE) != 0) {
            nextType = (TLAbsCodeType) StreamingUtils.readTLObject(stream, context);
        }
        if ((flags & FLAG_TIMEOUT) != 0) {
            timeout = StreamingUtils.readInt(stream);
        }
    }

    public String toString() {
        return "auth.sentCode#5e002502";
    }
}