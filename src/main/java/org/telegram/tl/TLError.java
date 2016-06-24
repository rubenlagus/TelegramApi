/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.tl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLError
 * @date 13/11/14
 */
public class TLError extends TLObject {
    public static final int CLASS_ID = 0xc4b9f9bb;
    public String text;
    protected int code;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.code, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.code = StreamingUtils.readInt(stream);
        this.text = StreamingUtils.readTLString(stream);
    }


    @Override
    public String toString() {
        return "tlError#c4b9f9bb";
    }
}