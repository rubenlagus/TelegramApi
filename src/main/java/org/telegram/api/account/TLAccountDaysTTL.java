/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 21/11/14.
 */
package org.telegram.api.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Time until an account is destroyed if the user hasn't logged in
 * @author Ruben Bermudez
 * @version 2.0
 * @date 21 /11/14
 */
public class TLAccountDaysTTL extends TLObject {
    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0xb8d0afdf;

    private int days; ///< Days until destruction if the account is unused

    /**
     * Instantiates a new TL account days tTL.
     */
    public TLAccountDaysTTL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets days until destruction.
     *
     * @return the days
     */
    public int getDays() {
        return this.days;
    }

    /**
     * Sets days until destruction.
     *
     * @param days the days
     */
    public void setDays(int days) {
        this.days = days;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.days, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.days = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "account.accountDaysTLL#b8d0afdf";
    }
}
